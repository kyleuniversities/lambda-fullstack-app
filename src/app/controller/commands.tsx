import { MapHelper } from '../../common/helper/MapHelper';
import { StringHelper } from '../../common/helper/StringHelper';
import { PromiseHelper } from '../../common/helper/js/PromiseHelper';
import { InvalidInputError } from '../../common/util/error';
import { RequestHelper } from '../helper/RequestHelper';
import { LambdaModel } from '../model/LambdaModel';

// Command Function Type
type CommandFunction = (
  parameters: string[],
  model: LambdaModel
) => Promise<void>;

// Command Functions
const echoText = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  model.setOutput(parameters[0]);
  return PromiseHelper.newConservativeVoidPromise();
};

const runLambdaFunction = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  const method = 'POST';
  const url = parameters[0];
  const args = parameters.slice(1);
  const bodyText = model.getBody();
  const environment = model.getEnvironment();
  const output = await RequestHelper.request(
    method,
    url,
    args,
    bodyText,
    environment
  );
  model.setOutput(JSON.stringify(output));
  return PromiseHelper.newConservativeVoidPromise();
};

const printEnvironmentVariables = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  const parts = StringHelper.newEmptyStringList();
  parts.push('<<Environment Variables>>');
  parts.push('');
  parts.push(`_SIZE: ${model.getEnvironmentSize()}`);
  parts.push('');
  model.forEachEnvironmentEntry((key: string, value: string) =>
    parts.push(`${key}: ${value}`)
  );
  model.setOutput(parts.join('\n'));
  return PromiseHelper.newConservativeVoidPromise();
};

const setEnvironmentValue = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  const key = parameters[0];
  const value = parameters[1];
  if (key.length === 0 || key.charAt(0) === '_' || key.toUpperCase() !== key) {
    throw new InvalidInputError('Invalid Environment Key');
  }
  model.setEnvironmentValue(key, value);
  return PromiseHelper.newConservativeVoidPromise();
};

const deleteEnvironmentEntry = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  model.deleteEnvironmentEntry(parameters[0]);
  return PromiseHelper.newConservativeVoidPromise();
};

const clearEnvironment = async (
  parameters: string[],
  model: LambdaModel
): Promise<void> => {
  model.clearEnvironment();
  return PromiseHelper.newConservativeVoidPromise();
};

// Command Map Maker
const makeCommandMap = () => {
  const commandMap = MapHelper.newMap<string, CommandFunction>();
  commandMap.set('echo', echoText);
  commandMap.set('run', runLambdaFunction);
  commandMap.set('env', printEnvironmentVariables);
  commandMap.set('export', setEnvironmentValue);
  commandMap.set('unset', deleteEnvironmentEntry);
  commandMap.set('clearenv', clearEnvironment);
  return commandMap;
};

// Command Map
export const COMMAND_MAP = makeCommandMap();

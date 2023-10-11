import { MapHelper } from '../../common/helper/MapHelper';
import { StringHelper } from '../../common/helper/StringHelper';
import { InvalidInputError } from '../../common/util/error';
import { LambdaModel } from '../model/LambdaModel';

// Command Function Type
type CommandFunction = (parameters: string[], model: LambdaModel) => void;

// Command Functions
const echoText = (parameters: string[], model: LambdaModel): void => {
  model.setOutput(parameters[0]);
};

const printEnvironmentVariables = (
  parameters: string[],
  model: LambdaModel
): void => {
  const parts = StringHelper.newEmptyStringList();
  parts.push('<<Environment Variables>>');
  parts.push('');
  parts.push(`_SIZE: ${model.getEnvironmentSize()}`);
  parts.push('');
  model.forEachEnvironmentEntry((key: string, value: string) =>
    parts.push(`${key}: ${value}`)
  );
  model.setOutput(parts.join('\n'));
};

const setEnvironmentValue = (
  parameters: string[],
  model: LambdaModel
): void => {
  const key = parameters[0];
  const value = parameters[1];
  if (key.length == 0 || key.charAt(0) == '_' || key.toUpperCase() !== key) {
    throw new InvalidInputError('Invalid Environment Key');
  }
  model.setEnvironmentValue(key, value);
};

const deleteEnvironmentEntry = (
  parameters: string[],
  model: LambdaModel
): void => {
  model.deleteEnvironmentEntry(parameters[0]);
};

const clearEnvironment = (parameters: string[], model: LambdaModel): void => {
  model.clearEnvironment();
};

// Command Map Maker
const makeCommandMap = () => {
  const commandMap = MapHelper.newMap<string, CommandFunction>();
  commandMap.set('echo', echoText);
  commandMap.set('env', printEnvironmentVariables);
  commandMap.set('export', setEnvironmentValue);
  commandMap.set('unset', deleteEnvironmentEntry);
  commandMap.set('clearenv', clearEnvironment);
  return commandMap;
};

// Command Map
export const COMMAND_MAP = makeCommandMap();

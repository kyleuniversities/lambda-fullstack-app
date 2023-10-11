import { MapHelper } from '../../common/helper/MapHelper';
import { StringHelper } from '../../common/helper/StringHelper';
import { StringEntryList, StringMap } from '../../common/util/string';
import { LambdaViewModel } from './LambdaViewModel';

const WELCOME_MESSAGE: string = 'Welcome to the Lambda Application!';

/**
 * Data model for Lambda Frontend App
 */
export class LambdaModel {
  // Instance Fields
  private historyCommandIndex: number;
  private historyCommandList: Command[];
  private environment: StringMap;
  private viewModel: LambdaViewModel;

  // New Instance Method
  public static newInstance(): LambdaModel {
    return new LambdaModel();
  }

  // Constructor Method
  protected constructor() {
    this.historyCommandIndex = 0;
    this.historyCommandList = [
      Command.newInstance(
        '',
        '',
        StringEntryList.newEmptyInstance(),
        StringEntryList.newEmptyInstance(),
        StringHelper.newStringMap()
      ),
    ];
    this.environment = StringHelper.newStringMap();
    this.viewModel = LambdaViewModel.newEmptyInstance(WELCOME_MESSAGE);
  }

  // Accessor methods
  public getCommand(): Command {
    return this.historyCommandList[this.historyCommandIndex];
  }

  public getEnvironment(): StringMap {
    return this.environment.copy();
  }

  public getEnvironmentValue(key: string): string {
    return this.environment.getValue(key);
  }

  public getEnvironmentSize(): number {
    return this.environment.size;
  }

  public getViewModel(): LambdaViewModel {
    return this.viewModel;
  }

  public getMessage(): string {
    return this.viewModel.getMessage();
  }

  public getInput(): string {
    return this.viewModel.getInput();
  }

  public getBody(): string {
    return this.viewModel.getBody();
  }

  public getOutput(): string {
    return this.viewModel.getOutput();
  }

  // Mutator methods
  public setEnvironmentValue(key: string, value: string) {
    this.environment.set(key, value);
  }

  public pushCommandHistory() {
    const command = this.compileNewCommand();
    this.historyCommandIndex = this.historyCommandList.length - 1;
    this.historyCommandList.push(command);
  }

  public setMessage(message: string): void {
    this.viewModel.setMessage(message);
  }

  public setInput(input: string): void {
    this.viewModel.setInput(input);
  }

  public setBody(body: string): void {
    this.viewModel.setBody(body);
  }

  public setOutput(output: string): void {
    this.viewModel.setOutput(output);
  }

  // Deleting Methods
  public deleteEnvironmentEntry(key: string): void {
    this.environment.delete(key);
  }

  // Iteration methods
  public forEachEnvironmentEntry(action: (key: string, value: string) => void) {
    MapHelper.forEach(this.environment, action);
  }

  // Clearing methods
  public clear(): void {
    this.clearHistory();
    this.clearEnvironment();
  }

  public clearHistory(): void {
    this.historyCommandIndex = 0;
    this.historyCommandList.splice(1);
  }

  public clearEnvironment(): void {
    this.environment.clear();
  }

  // Helper Methods
  private compileNewCommand(): Command {
    return Command.newInstance(
      this.getInput(),
      this.getBody(),
      StringEntryList.newEmptyInstance(),
      StringEntryList.newEmptyInstance(),
      this.environment
    );
  }
}

/**
 * Data for a single command
 */
export class Command {
  // Instance Fields
  private inputText: string;
  private bodyText: string;
  private images: StringEntryList;
  private files: StringEntryList;
  private environment: StringMap;

  // New Instance method
  public static newInstance(
    inputText: string,
    bodyText: string,
    images: StringEntryList,
    files: StringEntryList,
    environment: StringMap
  ) {
    return new Command(inputText, bodyText, images, files, environment);
  }

  // Constructor Method
  protected constructor(
    inputText: string,
    bodyText: string,
    images: StringEntryList,
    files: StringEntryList,
    environment: StringMap
  ) {
    this.inputText = inputText;
    this.bodyText = bodyText;
    this.images = images;
    this.files = files;
    this.environment = environment.copy();
  }

  // Accessor Methods
  public getInputText() {
    return this.inputText;
  }

  public getBodyText() {
    return this.bodyText;
  }

  public getImages() {
    return this.images.copy();
  }

  public getFiles() {
    return this.files.copy();
  }

  public getEnvironment() {
    return this.environment.copy();
  }
}

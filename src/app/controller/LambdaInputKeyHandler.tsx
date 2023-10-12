import { MapHelper } from '../../common/helper/MapHelper';
import { PromiseHelper } from '../../common/helper/js/PromiseHelper';
import { InputHelper } from '../helper/InputHelper';
import { LambdaModel } from '../model/LambdaModel';
import { COMMAND_MAP } from './commands';

export class LambdaInputKeyHandler {
  // Instance Fields
  private model: LambdaModel;

  // New Instance Method
  public static newInstance(model: LambdaModel): LambdaInputKeyHandler {
    return new LambdaInputKeyHandler(model);
  }

  // Constructor Method
  protected constructor(model: LambdaModel) {
    this.model = model;
  }

  // Main Instance Method
  public async handleKeyEvent(event: any): Promise<void> {
    switch (event.code) {
      case 'Tab':
        await this.handleTabEvent(event);
        break;
      case 'Enter':
        await this.handleEnterEvent(event);
        break;
    }
    return PromiseHelper.newConservativeVoidPromise();
  }

  // Key Handler Methods
  private async handleTabEvent(event: any): Promise<void> {
    alert('TAB has been entered');
    event.preventDefault();
    return PromiseHelper.newConservativeVoidPromise();
  }

  private async handleEnterEvent(event: any): Promise<void> {
    alert('ENTER has been entered');
    const inputText = this.model.getInput();
    const inputParts = InputHelper.splitInputText(
      this.model.getEnvironment(),
      inputText
    );
    const commandText = inputParts[0];
    const parameters = inputParts.slice(1);
    event.preventDefault();
    this.model.pushCommandHistory();
    await this.performCommand(commandText, parameters);
    return PromiseHelper.newConservativeVoidPromise();
  }

  private async performCommand(
    commandText: string,
    parameters: string[]
  ): Promise<void> {
    try {
      await MapHelper.getValue(COMMAND_MAP, commandText)(
        parameters,
        this.model
      );
    } catch (error) {
      if (error instanceof Error) {
        this.model.setOutput(`${error.constructor.name}: ${error.message}`);
      }
    }
    return PromiseHelper.newConservativeVoidPromise();
  }
}

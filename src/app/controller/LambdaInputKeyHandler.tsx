import { MapHelper } from '../../common/helper/MapHelper';
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
  public handleKeyEvent(event: any): void {
    switch (event.code) {
      case 'Tab':
        this.handleTabEvent(event);
        break;
      case 'Enter':
        this.handleEnterEvent(event);
        break;
    }
  }

  // Key Handler Methods
  private handleTabEvent(event: any): void {
    alert('TAB has been entered');
    event.preventDefault();
  }

  private handleEnterEvent(event: any): void {
    alert('ENTER has been entered');
    const inputText = this.model.getInput();
    const inputParts = InputHelper.splitInputText(
      this.model.getEnvironment(),
      inputText
    );
    const commandText = inputParts[0];
    const parameters = inputParts.slice(1);
    this.model.pushCommandHistory();
    this.performCommand(commandText, parameters);
    event.preventDefault();
  }

  private performCommand(commandText: string, parameters: string[]): void {
    try {
      MapHelper.getValue(COMMAND_MAP, commandText)(parameters, this.model);
    } catch (error) {
      if (error instanceof Error) {
        this.model.setOutput(`${error.constructor.name}: ${error.message}`);
      }
    }
  }
}

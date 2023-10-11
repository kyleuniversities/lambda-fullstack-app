import { InputHelper } from '../helper/InputHelper';
import { LambdaModel } from '../model/LambdaModel';

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
    const parts = InputHelper.splitInputText(
      this.model.getEnvironment(),
      inputText
    );
    const outputText = '<<size: ' + parts.length + '>>\n' + parts.join('\n');
    this.model.setOutput(outputText);
    event.preventDefault();
  }
}

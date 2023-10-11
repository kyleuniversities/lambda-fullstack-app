import { LambdaModel } from '../model/LambdaModel';
import { LambdaView } from '../view/LambdaView';
import { LambdaInputKeyHandler } from './LambdaInputKeyHandler';

export class LambdaController {
  // Instance Fields
  private model: LambdaModel;
  private view: () => JSX.Element;
  private inputKeyHandler: LambdaInputKeyHandler;

  // New Instance Method
  public static newInstance(): LambdaController {
    return new LambdaController();
  }

  // Constructor Method
  protected constructor() {
    this.model = LambdaModel.newInstance();
    this.view = this.initializeLambdaView();
    this.inputKeyHandler = LambdaInputKeyHandler.newInstance(this.model);
  }

  // Accessor Methods
  public getView(): () => JSX.Element {
    return this.view;
  }

  // Controller Methods
  private onInputKeyDown(event: any): void {
    this.inputKeyHandler.handleKeyEvent(event);
  }

  private onInputChange(event: any): void {
    this.model.setInput(event.target.value);
  }

  private onBodyChange(event: any): void {
    this.model.setBody(event.target.value);
  }

  // Initialization Methods
  private initializeLambdaView(): () => JSX.Element {
    return () =>
      LambdaView({
        model: this.model,
        onInputKeyDown: (event: any) => this.onInputKeyDown(event),
        onInputChange: (event: any) => this.onInputChange(event),
        onBodyChange: (event: any) => this.onBodyChange(event),
      });
  }
}

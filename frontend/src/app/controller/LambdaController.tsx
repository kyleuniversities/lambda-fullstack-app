import { PromiseHelper } from '../../common/helper/js/PromiseHelper';
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
  private async onInputKeyDown(event: any): Promise<void> {
    await this.inputKeyHandler.handleKeyEvent(event);
    return PromiseHelper.newConservativeVoidPromise();
  }

  private onInputChange(event: any): Promise<void> {
    this.model.setInput(event.target.value);
    return PromiseHelper.newConservativeVoidPromise();
  }

  private onBodyChange(event: any): Promise<void> {
    this.model.setBody(event.target.value);
    return PromiseHelper.newConservativeVoidPromise();
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

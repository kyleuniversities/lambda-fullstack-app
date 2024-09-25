/**
 * Data for UI contents of lambda view
 */
export class LambdaViewModel {
  // Instance Fields
  private message: string;
  private input: string;
  private body: string;
  private output: string;

  // New Instance Methods
  public static newEmptyInstance(message: string): LambdaViewModel {
    return LambdaViewModel.newInstance(message, '', '', '');
  }

  public static newInstance(
    message: string,
    input: string,
    body: string,
    output: string
  ): LambdaViewModel {
    return new LambdaViewModel(message, input, body, output);
  }

  // Constructor Method
  protected constructor(
    message: string,
    input: string,
    body: string,
    output: string
  ) {
    this.message = message;
    this.input = input;
    this.body = body;
    this.output = output;
  }

  // Accessor methods
  public getMessage(): string {
    return this.message;
  }

  public getInput(): string {
    return this.input;
  }

  public getBody(): string {
    return this.body;
  }

  public getOutput(): string {
    return this.output;
  }

  // Mutator Methods
  public setMessage(message: string): void {
    this.message = message;
  }

  public setInput(input: string): void {
    this.input = input;
  }

  public setBody(body: string): void {
    this.body = body;
  }

  public setOutput(output: string): void {
    this.output = output;
  }
}

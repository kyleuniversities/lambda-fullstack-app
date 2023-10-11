import { InputTextSplitter } from '../util/input';

export class InputHelper {
  private constructor() {}

  /**
   * Splits an input into parts
   */
  public static splitInputText(text: string): string[] {
    return InputTextSplitter.newInstance().split(text);
  }
}

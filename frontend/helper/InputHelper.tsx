import { StringMap } from "../common/util/string";
import { InputTextSplitter } from "../util/input";

export class InputHelper {
  private constructor() {}

  /**
   * Splits an input into parts
   */
  public static splitInputText(environment: StringMap, text: string): string[] {
    return InputTextSplitter.newInstance(environment).split(text);
  }
}

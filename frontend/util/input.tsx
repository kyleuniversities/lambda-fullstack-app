import { ArrayHelper } from "../common/helper/ArrayHelper";
import { StringHelper } from "../common/helper/string/StringHelper";
import { ifThen } from "../common/util/conditional";
import { StringMap } from "../common/util/string";

const QUOTE_ESCAPE_CHARACTER_MAP = StringHelper.newStringMapFromDoubleArray([
  ["\\", "\\"],
  ['"', '"'],
  ["n", "\n"],
  ["t", "\t"],
]);

/**
 * Utility class for splitting input text into parts
 */
export class InputTextSplitter {
  // Instance Fields
  private environment: StringMap;
  private text: string;
  private parts: string[];
  private part: string[];
  private isWithinQuotes;
  private i;

  // New Instance Method
  public static newInstance(environment: StringMap): InputTextSplitter {
    return new InputTextSplitter(environment);
  }

  // Constructor Method
  protected constructor(environment: StringMap) {
    this.environment = environment;
    this.text = "";
    this.parts = [];
    this.part = [];
    this.isWithinQuotes = false;
    this.i = 0;
  }

  // Main Instance Method
  public split(text: string): string[] {
    this.reset(text);
    this.forEachIterationCharacter((ch: string) => {
      const characterIsWithinQuotes = this.isWithinQuotes;
      ifThen(characterIsWithinQuotes, () => this.processQuotedCharacter(ch));
      ifThen(!characterIsWithinQuotes, () => this.processUnquotedCharacter(ch));
    });
    this.popPartIfNotEmpty();
    return ArrayHelper.clone(this.parts);
  }

  // Processing Methods
  private processQuotedCharacter(ch: string): void {
    this.processCharacter(ch, false, false, true);
  }

  private processUnquotedCharacter(ch: string): void {
    this.processCharacter(ch, true, true, false);
  }

  private processCharacter(
    ch: string,
    canToggleToWithinQuotes: boolean,
    canPopParts: boolean,
    canEscapeCharacters: boolean,
  ): void {
    switch (ch) {
      case '"':
        this.isWithinQuotes = canToggleToWithinQuotes;
        this.appendPart(ch);
        break;
      case " ":
        ifThen(canPopParts, () => this.popPartIfNotEmpty());
        ifThen(!canPopParts, () => this.appendPart(ch));
        break;
      case "\\":
        ifThen(canEscapeCharacters, () => this.processEscapeCharacter());
        ifThen(!canEscapeCharacters, () => this.appendPart(ch));
        break;
      default:
        this.appendPart(ch);
    }
  }

  private processEscapeCharacter(): void {
    this.appendPart(QUOTE_ESCAPE_CHARACTER_MAP.getValue(this.nextCharAt()));
    this.i++;
  }

  // Iteration Methods
  private forEachIterationCharacter(action: (ch: string) => void): void {
    while (this.i < this.text.length) {
      action(this.text.charAt(this.i));
      this.i++;
    }
  }

  // Helper Methods
  private nextCharAt(): string {
    return this.text.charAt(this.i + 1);
  }

  private appendPart(ch: string): void {
    this.part.push(ch);
  }

  private popPartIfNotEmpty(): void {
    if (this.part.length > 0) {
      let partText = this.compilePartText();
      this.parts.push(partText);
      ArrayHelper.clear(this.part);
    }
  }

  private compilePartText(): string {
    const rawPartText = this.part.join("");
    const firstCharacter = rawPartText.charAt(0);
    switch (firstCharacter) {
      case '"':
        return rawPartText.substring(1, rawPartText.length - 1);
      case "$":
        return this.environment.getValue(rawPartText.substring(1));
      default:
        return rawPartText;
    }
  }

  // Functional Initialization Methods
  private reset(text: string): void {
    this.text = text;
    this.parts = [];
    this.part = [];
    this.isWithinQuotes = false;
    this.i = 0;
  }
}

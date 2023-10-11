import { ArrayHelper } from '../../common/helper/ArrayHelper';
import { StringHelper } from '../../common/helper/StringHelper';
import { ifThen } from '../../common/util/conditional';
import { StringMap } from '../../common/util/string';

const QUOTE_ESCAPE_CHARACTER_MAP = StringHelper.newStringMapFromDoubleArray([
  ['\\', '\\'],
  ['"', '"'],
  ['n', '\n'],
  ['t', '\t'],
]);

const UNQUOTE_ESCAPE_CHARACTER_MAP = StringHelper.newStringMapFromDoubleArray([
  ['$', '$'],
]);

/**
 * Utility class for splitting input text into parts
 */
export class InputTextSplitter {
  // Instance Fields
  private text: string;
  private parts: string[];
  private part: string[];
  private isWithinQuotes;
  private i;

  // New Instance Method
  public static newInstance(): InputTextSplitter {
    return new InputTextSplitter();
  }

  // Constructor Method
  protected constructor() {
    this.text = '';
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
    this.processCharacter(ch, false, false, QUOTE_ESCAPE_CHARACTER_MAP);
  }

  private processUnquotedCharacter(ch: string): void {
    this.processCharacter(ch, true, true, UNQUOTE_ESCAPE_CHARACTER_MAP);
  }

  private processCharacter(
    ch: string,
    canToggleToWithinQuotes: boolean,
    canPopParts: boolean,
    escapeCharacterMap: StringMap
  ): void {
    switch (ch) {
      case '"':
        this.isWithinQuotes = canToggleToWithinQuotes;
        this.appendPart(ch);
        break;
      case ' ':
        ifThen(canPopParts, () => this.popPartIfNotEmpty());
        ifThen(!canPopParts, () => this.appendPart(ch));
        break;
      case '\\':
        this.appendPart(escapeCharacterMap.getValue(this.nextCharAt()));
        this.i++;
        break;
      default:
        this.appendPart(ch);
    }
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
    let partText = this.part.join('');
    if (partText.charAt(0) === '"') {
      partText = partText.substring(1, partText.length - 1);
    }
    return partText;
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

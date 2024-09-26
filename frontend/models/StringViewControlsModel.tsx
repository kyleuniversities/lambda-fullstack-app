import { StringHelper } from "@/common/helper/string/StringHelper";

/**
 * Structure class for String View Model
 */
export default class StringViewControlsModel {
  // Instance Fields
  private primaryMode: string;
  private secondaryMode: string;

  // New Instance Methods
  static newInitialInstance(): StringViewControlsModel {
    return StringViewControlsModel.newInstance("replace", "normal");
  }

  static newInstance(
    primaryMode: string,
    secondaryMode: string,
  ): StringViewControlsModel {
    return new StringViewControlsModel(primaryMode, secondaryMode);
  }

  // Constructor Method
  constructor(primaryMode: string, secondaryMode: string) {
    this.primaryMode = primaryMode;
    this.secondaryMode = secondaryMode;
  }

  // Accessor Methods
  getPrimaryMode(): string {
    return this.primaryMode;
  }

  getSecondaryMode(): string {
    return this.secondaryMode;
  }

  getModeKey(): string {
    return `${this.primaryMode.replaceAll(" ", "_")}-${this.secondaryMode.replaceAll(" ", "_")}`;
  }

  getFieldTitle1(): string {
    return this.collectFieldTitle1();
  }

  getFieldTitle2(): string {
    return this.collectFieldTitle2();
  }

  getFieldPlaceholder1(): string {
    return this.collectFieldPlaceholder1();
  }

  getFieldPlaceholder2(): string {
    return this.collectFieldPlaceholder2();
  }

  getBodyPlaceholder(): string {
    return this.collectBodyPlaceholder();
  }

  getModeTitle(): string {
    return this.collectModeTitle();
  }

  getModeDescription(): string {
    return this.collectModeDescription();
  }

  // Operant Class Methods
  static getSecondaryModeChoices(primaryMode: string): string[] {
    switch (primaryMode) {
      case "replace":
        return ["normal", "cased", "serial"];
      case "code":
        return ["convert_to_text", "convert_to_code"];
      case "tab":
        return ["normalize", "tab_amount"];
    }
    return [];
  }

  // Operant Methods
  copyWithPrimaryModeChange(primaryMode: string): StringViewControlsModel {
    // Return self if no change
    if (this.primaryMode === primaryMode) {
      return this;
    }
    // Return new model if change
    return StringViewControlsModel.newInstance(
      primaryMode,
      StringViewControlsModel.getSecondaryModeChoices(primaryMode)[0],
    );
  }

  copyWithSecondaryModeChange(secondaryMode: string): StringViewControlsModel {
    // Return self if no change
    if (this.secondaryMode === secondaryMode) {
      return this;
    }
    // Return new model if change
    return StringViewControlsModel.newInstance(this.primaryMode, secondaryMode);
  }

  // Major Methods
  collectFieldTitle1(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
      case "replace-cased":
      case "replace-serial":
        return "Target";
      case "code-convert_to_text":
      case "code-convert_to_code":
      case "tab-normalize":
        break;
      case "tab-tab_amount":
        return "Amount";
    }
    return StringHelper.newEmptyString();
  }

  collectFieldTitle2(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
      case "replace-cased":
        return "Replacement";
      case "replace-serial":
        return "Replacements";
      case "code-convert_to_text":
      case "code-convert_to_code":
      case "tab-normalize":
      case "tab-tab_amount":
        break;
    }
    return StringHelper.newEmptyString();
  }

  collectFieldPlaceholder1(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
      case "replace-cased":
        return "Enter the text you want to replace";
      case "replace-serial":
        return "Enter the text you want to replace";
      case "code-convert_to_text":
      case "code-convert_to_code":
      case "tab-normalize":
        break;
      case "tab-tab_amount":
        return "Enter the number of tabs you would like to preceed the text";
    }
    return StringHelper.newEmptyString();
  }

  collectFieldPlaceholder2(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
      case "replace-cased":
        return "Enter the text you would like to fill in as the replacement";
      case "replace-serial":
        return "Enter a space-separated list of texts you would like to fill in as serial replacements";
      case "code-convert_to_text":
      case "code-convert_to_code":
      case "tab-normalize":
      case "tab-tab_amount":
        break;
    }
    return StringHelper.newEmptyString();
  }

  collectBodyPlaceholder(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
      case "replace-cased":
        return "Enter the collective text you would like to perform the replacement on";
      case "code-convert_to_text":
        return "Enter the String literal you would like to convert to text";
      case "code-convert_to_code":
        return "Enter the text you would like to convert to a String literal";
      case "tab-normalize":
        return "Enter the text you would normalize tabs on";
        break;
      case "tab-tab_amount":
        return "Enter the text you would set the preceeding tab indent on";
    }
    return StringHelper.newEmptyString();
  }

  collectModeTitle(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
        return "Replace All";
      case "replace-cased":
        return "Replace Cased";
      case "replace-serial":
        return "Replace All Serial";
      case "code-convert_to_text":
        return "Convert to Text";
      case "code-convert_to_code":
        return "Convert to Code";
      case "tab-normalize":
        return "Normalize Tabs";
      case "tab-tab_amount":
        return "Set Indent Tab Amount";
    }
    return StringHelper.newEmptyString();
  }

  collectModeDescription(): string {
    switch (this.getModeKey()) {
      case "replace-normal":
        return "Replaces all instances of a target substring with a replacement.";
      case "replace-cased":
        return "Performs a replace-all but is consistent with the capitalization of the first characters";
      case "replace-serial":
        return "Replaces all instances of a target substring with a circular ordered set of replacements";
      case "code-convert_to_text":
        return "Converts a String literal into the text it depicts.  Unicode conversion not supported yet.";
      case "code-convert_to_code":
        return "Converts text into a String literal.  Unicode conversion not supported yet.";
      case "tab-normalize":
        return "Sets the indent of each line in a text to 0.  Relative indents are kept.";
      case "tab-tab_amount":
        return "Sets the indent of each line in a text to a specified number of tabs.";
    }
    return StringHelper.newEmptyString();
  }
}

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
        return StringViewControlsModel.newInstance("replace", "normal")
    }

    static newInstance(primaryMode: string, secondaryMode: string): StringViewControlsModel {
        return new StringViewControlsModel(primaryMode, secondaryMode)
    }
    
    // Constructor Method
    constructor(primaryMode: string, secondaryMode: string) {
        this.primaryMode = primaryMode
        this.secondaryMode = secondaryMode
    }

    // Accessor Methods
    getPrimaryMode(): string {
        return this.primaryMode
    }
    
    getSecondaryMode(): string {
        return this.secondaryMode
    }

    getModeKey(): string {
        return `${this.primaryMode.replaceAll(" ", "_")}-${this.secondaryMode.replaceAll(" ", "_")}`
    }

    getFieldTitle1(): string {
       return this.collectFieldTitle1() 
    }

    getFieldTitle2(): string {
        return this.collectFieldTitle2()
    }

    // Major Methods
    collectFieldTitle1(): string {
        switch (this.getModeKey()) {
            case "replace-normal":
            case "replace-cased":
            case "replace-serial":
                return "Target"
            case "code-convert_to_text":
                return "String Literal"
            case "code-convert_to_code":
                return "Text"
            case "code-normalize":
                break;
            case "code-tab_amount":
                return "Amount"
        }
        return StringHelper.newEmptyString()
    }
 
    collectFieldTitle2(): string {
        switch (this.getModeKey()) {
            case "replace-normal":
            case "replace-cased":
                return "Replacement"
            case "replace-serial":
                return "Replacements"
            case "code-convert_to_text":
            case "code-convert_to_code":
            case "code-normalize":
            case "code-tab_amount":
                break;
        }
        return StringHelper.newEmptyString()
    }
}

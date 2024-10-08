import { StringCodeHelper } from "@/common/helper/string/StringCodeHelper";
import { StringMap } from "@/common/util/string";
import { InputHelper } from "@/helper/InputHelper";
import { RequestHelper } from "@/helper/RequestHelper";

export const lambdaRequest = async (
  input: string,
  body: string,
): Promise<string> => {
  const inputParts = InputHelper.splitInputText(StringMap.newInstance(), input);
  try {
    const rawOutput = await RequestHelper.request(
      "POST",
      inputParts[1],
      inputParts.slice(2),
      body,
      StringMap.newInstance(),
    );
    const codeOutput = JSON.stringify(rawOutput);
    if (!codeOutput.startsWith('"')) {
      throw new Error(
        "Unable to perform command.  Please check the formatting of the command's parameters.",
      );
    }
    const output = StringCodeHelper.toText(codeOutput);
    return output;
  } catch (error) {
    return "Error: Unable to process your command.  Please check the formatting of your command parameters.";
  }
};

import { ArrayHelper } from '../../common/helper/ArrayHelper';
import { StringCodeHelper } from '../../common/helper/string/StringCodeHelper';
import { StringMap } from '../../common/util/string';

const DEFAULT_HOST = 'http://localhost:8080';
const encodeArray = (array: string[]): string[] => {
  return ArrayHelper.map(array, (item: string) =>
    StringCodeHelper.toCode(item)
  );
};
const encodeMap = (map: StringMap): StringMap => {
  return map.mapValues((string: string) => StringCodeHelper.toCode(string));
};

export class RequestHelper {
  private constructor() {}

  /**
   * Performs a Lambda http request
   */
  public static async request(
    method: string,
    url: string,
    args: string[],
    bodyText: string,
    environment: StringMap
  ): Promise<string> {
    const host = process.env['REACT_APP_API_HOST'] || DEFAULT_HOST;
    const fullUrl = `${host}/${url}`;
    const headers = { 'Content-Type': 'application/json' };
    const body = {
      arguments: encodeArray(args),
      bodyText: StringCodeHelper.toCode(bodyText),
      environment: encodeMap(environment).toJson(),
    };
    const options = {
      mode: 'cors' as RequestMode,
      method,
      headers,
      body: JSON.stringify(body),
    };
    alert('REQUEST: ' + fullUrl);
    alert('EXAMPLE: ' + JSON.stringify({ message: 'Hi' }));
    alert('BODY: ' + JSON.stringify(body));
    alert('OPTIONS: ' + JSON.stringify(options));
    return fetch(fullUrl, options)
      .then((data) => data.json())
      .catch((error) => {
        console.log('ERROR_MESSAGE: ' + error.message);
      });
  }
}

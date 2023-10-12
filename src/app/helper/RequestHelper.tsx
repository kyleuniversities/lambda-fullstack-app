import { StringMap } from '../../common/util/string';

const DEFAULT_HOST = 'http://localhost:8080';

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
      args,
      bodyText,
      environment: environment.toJson(),
    };
    const options = {
      mode: 'cors' as RequestMode,
      method,
      headers,
      body: JSON.stringify(body),
    };
    alert('REQUEST: ' + fullUrl);
    return fetch(fullUrl, options)
      .then((data) => {
        return data.json();
      })
      .catch((error) => {
        console.log('ERROR_MESSAGE: ' + error.message);
      });
  }
}

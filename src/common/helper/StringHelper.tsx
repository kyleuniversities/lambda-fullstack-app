import { StringMap } from '../util/string';

export class StringHelper {
  private constructor() {}

  /**
   * Creates a new String Map
   */
  public static newStringMap(
    keyValuePairList?: Iterable<readonly [string, string]>
  ): StringMap {
    return StringMap.newInstance(keyValuePairList);
  }
}

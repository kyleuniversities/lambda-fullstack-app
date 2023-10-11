import { StringMap } from '../util/string';
import { ArrayHelper } from './ArrayHelper';

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

  /**
   * Creates a new String Map
   */
  public static newStringMapFromDoubleArray(array: string[][]): StringMap {
    const map = StringHelper.newStringMap();
    ArrayHelper.forEach(array, (keyValuePair: string[]) => {
      map.set(keyValuePair[0], keyValuePair[1]);
    });
    return map;
  }
}

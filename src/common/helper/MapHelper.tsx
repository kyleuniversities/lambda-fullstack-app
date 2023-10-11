export class MapHelper {
  private constructor() {}

  /**
   * Iterates through all entries in a map
   */
  public static forEach<K, V>(
    map: Map<K, V>,
    action: (key: K, value: V) => void
  ): void {
    map.forEach((value: V, key: K) => action(key, value));
  }
}

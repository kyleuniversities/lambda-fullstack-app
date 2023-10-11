export class ArrayHelper {
  private constructor() {}

  /**
   * Clears an array
   */
  public static clear<T>(array: Array<T>): void {
    array.splice(0);
  }

  /**
   * Iterates through all elements in an array
   */
  public static forEach<T>(array: Array<T>, action: (item: T) => void): void {
    array.forEach((item: T) => action(item));
  }
}

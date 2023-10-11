/**
 * Utility function for performing concise if-statements
 */
export const ifThen = (condition: boolean, action: () => void) => {
  if (condition) {
    action();
  }
};

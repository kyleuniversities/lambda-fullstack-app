/**
 * Utility function for performing concise if-statements
 */
export const ifThen = (condition: boolean, action: () => void) => {
  if (condition) {
    action();
  }
};

/**
 * Utility function for performing more elegant ternary operations
 */
export const ifElseReturn = <T,>(
  condition: boolean,
  acceptanceValue: () => T,
  rejectionValue: () => T
) => {
  if (condition) {
    return acceptanceValue();
  }
  return rejectionValue();
};

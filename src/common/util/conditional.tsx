export const ifThen = (condition: boolean, action: () => void) => {
  if (condition) {
    action();
  }
};

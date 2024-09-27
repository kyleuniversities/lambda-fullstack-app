export const nextLambdaMessage = (): string => {
  const index = nextInt(messages.length);
  return welcomeMessage + messages[index]();
};

export const loadingLambdaMessage = (): string => {
  return welcomeMessage + "Loading...";
};

const nextInt = (bound: number) => {
  return Math.floor(Math.random() * bound * 0.999999);
};

const nextRange = (lower: number, upper: number) => {
  return lower + nextInt(upper - lower);
};

const welcomeMessage =
  "Welcome to the Lambda Custom Console!  \n\nHere, you can run various types of utility commands.  An example command would be:\n\n";

const messages = [
  () =>
    `run number/integer/add ${nextRange(1, 10)} ${nextRange(1, 20)} ${nextRange(1, 30)}`,
  () =>
    `run number/integer/multiply ${nextRange(1, 10)} ${nextRange(1, 20)} ${nextRange(1, 30)}`,
  () => `run number/double/divide ${nextRange(500, 1000)} ${nextRange(1, 100)}`,
  () =>
    `run number/double/subtract ${nextRange(100, 1000)} ${nextRange(1, 100)}`,
  () => `run number/double/evaluate "x^2+y^2" 6 8`,
  () =>
    `run number/double/evaluate "x^2+y^2" ${nextRange(1, 30)} ${nextRange(1, 30)}`,
  () =>
    `run number/double/evaluate "e^{x^2+2xy+y^2}" ${nextRange(1, 20) / 10} ${nextRange(1, 20) / 10}`,
  () => `run number/double/evaluate "x+e^{yiπ/2}" 2 1`,
];

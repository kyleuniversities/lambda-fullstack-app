export const nextLambdaMessage = (): string => {
  const index = Math.floor(Math.random() * 0.999 * messages.length);
  return messages[index];
};

const messages = ["Welcome to the Lambda Application!"];

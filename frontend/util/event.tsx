/**
 * Utility function for doing nothing in response to an event
 */
export const doNothing = (event: any): void => {};

/**
 * Utility function for waiting
 */
export const wait = async (time: number): Promise<void> => {
  return await new Promise((resolve: any) => setTimeout(resolve, time));
};

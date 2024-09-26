/**
 * For translating to px
 */
export const toPx = (value: number): string => {
  return `${value}px`;
};

/**
 * For translating from px
 */
export const fromPx = (text: string): number => {
  return Number(text.split(/[px%]/)[0]);
};

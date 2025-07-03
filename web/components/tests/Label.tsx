import { LabelHTMLAttributes, PropsWithChildren } from 'react';

export default function Label({
  htmlFor,
  children,
}: LabelHTMLAttributes<HTMLLabelElement> & PropsWithChildren) {
  return (
    <label
      htmlFor={htmlFor}
      className="font-size-sm font-semibold text-gray-700"
    >
      {children}
    </label>
  );
}

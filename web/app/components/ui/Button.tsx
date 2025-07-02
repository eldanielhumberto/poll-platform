'use client';

import { ButtonHTMLAttributes, PropsWithChildren } from 'react';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
	/**
	 * The variant of the button.
	 * @default 'default'
	 */
	variant?: 'default' | 'primary' | 'secondary' | 'outline' | 'ghost';

	/**
	 * The size of the button.
	 * @default 'md'
	 */
	size?: 'sm' | 'md' | 'lg';
}

export default function Button({
	variant,
	size,
	children,
}: Props & PropsWithChildren) {
	const variantClasses = {
		default: 'bg-gray-900 text-white hover:bg-gray-800',
		primary: 'bg-blue-600 text-white hover:bg-blue-700',
		secondary: 'bg-white text-gray-800 hover:bg-gray-100',
		outline: 'border border-gray-300 text-gray-800 hover:bg-gray-100',
		ghost: 'bg-transparent text-gray-800 hover:bg-gray-100',
	};

	const sizeClasses = {
		sm: 'px-3 py-1 text-sm',
		md: 'px-4 py-2 text-base',
		lg: 'px-9 py-3 text-lg',
	};

	const classButton = `rounded-md transition-colors font-medium hover:cursor-pointer flex items-center gap-2 ${
		variant ? variantClasses[variant] : variantClasses.default
	} ${size ? sizeClasses[size] : sizeClasses.md}`;

	return <button className={classButton}>{children}</button>;
}

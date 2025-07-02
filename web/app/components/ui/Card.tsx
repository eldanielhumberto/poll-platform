'use client';

import { PropsWithChildren } from 'react';

// This component is used to create a card-like UI element with a shadow effect
export function Card({ children }: PropsWithChildren) {
	return (
		<div className='shadow-lg hover:shadow-xl transition-shadow'>
			{children}
		</div>
	);
}

// These components are used to create the header, title, and description of the card
// They can be used to structure the content inside the Card component
export function CardHeader({ children }: PropsWithChildren) {
	return <div className='p-6'>{children}</div>;
}

export function CardTitle({ children }: PropsWithChildren) {
	return <h3 className='text-xl font-semibold mb-2'>{children}</h3>;
}

export function CardDescription({ children }: PropsWithChildren) {
	return <p className='text-gray-600'>{children}</p>;
}

import { Question } from './Question';
import { User } from './User';

export interface Survey {
  id: string;
  title: string;
  description: string;
  category: {
    name: string;
    color: string;
  };
  visits: number;
  answers: number;
  questions?: Question[];
  author: User;
  createdAt: string;
}

import { Question } from './Question';
import { User } from './User';

export interface Survey {
  id: string;
  title: string;
  description: string;
  category: {
    id: string;
    name: string;
    color: string;
  };
  visits?: number;
  answers?: number;
  questions: Question[];
  author?: User;
  createdAt: string;
}

export interface SurveyDataToSend {
  title: string;
  description: string;
  categoryId: string;
  questions: {
    questionText: string;
    options: string[];
  }[];
}

export interface SurveySummary {
  id: string;
  title: string;
}

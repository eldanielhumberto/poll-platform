import { Option } from './Option';

export interface Question {
  id: string;
  questionText: string;
  options: Option[];
}

export interface QuestionSummary {
  id: string;
  questionText: string;
}

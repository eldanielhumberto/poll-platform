import { OptionSummary } from './Option';
import { QuestionSummary } from './Question';
import { SurveySummary } from './Survey';
import { UserSummary } from './User';

export interface Response {
  id: string;
  user: UserSummary;
  respondent: UserSummary;
  survey: SurveySummary;
  question: QuestionSummary;
  option: OptionSummary;
}

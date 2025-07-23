'use client';

import { Dispatch, SetStateAction } from 'react';

import QuestionsList from './questions/QuestionsList';
import ProgressBar from './ProgressBar';

import { Button } from '../ui/button';

import { Question } from '@/interfaces/Question';
import { Survey } from '@/interfaces/Survey';
import { User } from '@/interfaces/User';

interface Props {
  setCurrentQuestion: Dispatch<SetStateAction<number>>;
  currentQuestion: number;

  handleSingleChoice: (questionId: string, value: string) => void;
  handleSubmit?: (answers: Record<string, string>) => void;

  answers: Record<string, string>;
  user: User | null;
  survey: Survey;
}

export default function SurveyPendingQuestions({
  setCurrentQuestion,
  currentQuestion,
  handleSingleChoice,
  handleSubmit,
  answers,
  user,
  survey,
}: Props) {
  const isQuestionAnswered = (question: Question) => {
    const answer = answers[question.questionText];
    return answer && answer.trim() !== '';
  };

  const canSubmit = survey.questions.every((q) => isQuestionAnswered(q));

  return (
    <>
      {/* Progress Bar */}
      <ProgressBar
        currentQuestion={currentQuestion}
        questions={survey.questions}
      />

      {/* Questions */}
      <QuestionsList
        questions={survey.questions}
        answers={answers}
        currentQuestion={currentQuestion}
        handleSingleChoice={handleSingleChoice}
      />

      {/* Navigation */}
      <div
        className={`flex justify-${
          user ? 'between' : 'center gap-3 flex-col'
        } items-center mt-8`}
      >
        {user ? (
          <>
            <Button
              variant="outline"
              onClick={() =>
                setCurrentQuestion(Math.max(0, currentQuestion - 1))
              }
              disabled={currentQuestion === 0}
            >
              Anterior
            </Button>

            <div className="flex space-x-2">
              {survey.questions.map((_, index) => (
                <div
                  key={index}
                  className={`w-2 h-2 rounded-full ${
                    index === currentQuestion
                      ? 'bg-blue-600'
                      : index < currentQuestion
                      ? 'bg-green-600'
                      : 'bg-gray-300'
                  }`}
                />
              ))}
            </div>

            {currentQuestion < survey.questions.length - 1 ? (
              <Button
                onClick={() => setCurrentQuestion(currentQuestion + 1)}
                disabled={
                  !isQuestionAnswered(survey.questions[currentQuestion])
                }
              >
                Siguiente
              </Button>
            ) : (
              <Button
                onClick={() => handleSubmit && handleSubmit(answers)}
                disabled={!canSubmit}
                className="bg-green-600 hover:bg-green-700"
              >
                Enviar Respuestas
              </Button>
            )}
          </>
        ) : (
          <>
            <p>Para responder, inicia sesión o crea una cuenta.</p>
            <div className="space-x-4">
              <Button variant="outline">Iniciar sesion</Button>
              <Button>Crear cuenta</Button>
            </div>
          </>
        )}
      </div>
    </>
  );
}

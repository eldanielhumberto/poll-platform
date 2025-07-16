'use client';

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Survey } from '@/interfaces/Survey';
import SurveyDetails from './SurveyDetails';
import ProgressBar from '@/components/SurveyView/ProgressBar';
import QuestionsList from '@/components/SurveyView/questions/QuestionsList';
import { Question } from '@/interfaces/Question';

interface SurveyPreviewProps {
  survey: Survey;
  handleSubmit?: (answers: Record<string, string>) => void;
  isEditor?: boolean;
}

export function SurveyView({
  survey,
  handleSubmit,
  isEditor,
}: SurveyPreviewProps) {
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [answers, setAnswers] = useState({} as Record<string, string>);

  const isQuestionAnswered = (question: Question) => {
    const answer = answers[question.questionText];
    return answer && answer.trim() !== '';
  };

  const handleSingleChoice = (questionId: string, value: string) => {
    setAnswers((prev) => ({ ...prev, [questionId]: value }));
  };

  if (
    isEditor &&
    (survey.questions.length === 0 ||
      survey.title.length === 0 ||
      survey.description.length === 0 ||
      survey.category.name.length === 0)
  ) {
    return (
      <Card>
        <CardContent className="py-12 text-center">
          <p className="text-gray-500">
            Rellena la informacion basica y agrega preguntas para ver la vista
            previa de tu encuesta
          </p>
        </CardContent>
      </Card>
    );
  }

  const canSubmit = survey.questions.every((q) => isQuestionAnswered(q));

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      {/* Survey details */}
      <SurveyDetails survey={survey} />

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
      <div className="flex justify-between items-center mt-8">
        <Button
          variant="outline"
          onClick={() => setCurrentQuestion(Math.max(0, currentQuestion - 1))}
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
            disabled={!isQuestionAnswered(survey.questions[currentQuestion])}
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
      </div>
    </div>
  );
}

import { Flag, Share2, Users } from 'lucide-react';
import dayjs from 'dayjs';

import { Survey } from '@/interfaces/Survey';

import { Card, CardDescription, CardHeader, CardTitle } from './ui/card';
import { Avatar, AvatarFallback } from './ui/avatar';
import { Button } from './ui/button';
import { Badge } from './ui/badge';

interface Props {
  survey: Survey;
}

export default function SurveyDetails({ survey }: Props) {
  return (
    <Card className="border-0 shadow-lg mb-8">
      <CardHeader>
        <div className="flex items-start justify-between mb-4">
          <Badge className={survey.category.color}>
            {survey.category.name}
          </Badge>
          <div className="flex items-center space-x-4">
            <Button variant="ghost" size="sm">
              <Share2 className="h-4 w-4" />
            </Button>
            <Button variant="ghost" size="sm">
              <Flag className="h-4 w-4" />
            </Button>
          </div>
        </div>

        <CardTitle className="text-2xl mb-2">{survey.title}</CardTitle>
        <CardDescription className="text-base">
          {survey.description}
        </CardDescription>

        <div className="flex items-center justify-between mt-6">
          <div className="flex items-center space-x-3">
            <Avatar className="h-10 w-10">
              <AvatarFallback>
                {survey.author.username
                  .split(' ')
                  .map((n) => n[0])
                  .join('')}
              </AvatarFallback>
            </Avatar>
            <div>
              <p className="font-medium">{survey.author.username}</p>
              <p className="text-sm text-gray-500">
                Creada el {dayjs(survey.createdAt).format('DD MMMM YYYY')}
              </p>
            </div>
          </div>
          <div className="flex items-center space-x-4 text-sm text-gray-600">
            <div className="flex items-center space-x-1">
              <Users className="h-4 w-4" />
              <span>{survey.answers} respuestas</span>
            </div>
          </div>
        </div>
      </CardHeader>
    </Card>
  );
}

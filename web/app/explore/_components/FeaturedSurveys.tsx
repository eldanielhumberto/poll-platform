import { TrendingUp, Users } from 'lucide-react';
import Link from 'next/link';

import { Avatar, AvatarFallback } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { Survey } from '@/interfaces/Survey';
import { use } from 'react';
import { ServerResponse } from '@/interfaces/ServerResponse';
import { Badge } from '@/components/ui/badge';

interface Props {
  surveys: Promise<ServerResponse<Survey[]>>;
}

export default function FeaturedSurveys({ surveys }: Props) {
  const { data } = use(surveys);

  return (
    <div className="mb-8">
      <h2 className="text-xl font-semibold text-gray-900 mb-4 flex items-center">
        <TrendingUp className="h-5 w-5 mr-2 text-orange-600" />
        Encuestas Destacadas
      </h2>
      <div className="grid md:grid-cols-2 gap-6">
        {data.slice(0, 2).map((survey) => (
          <Card
            key={survey.id}
            className="border-0 shadow-lg hover:shadow-xl transition-shadow bg-gradient-to-br from-blue-50 to-purple-50"
          >
            <CardHeader>
              <div className="flex items-start justify-between">
                <Badge className="bg-blue-100 text-blue-800">Tecnologia</Badge>
              </div>
              <CardTitle className="text-lg">{survey.title}</CardTitle>
              <CardDescription>{survey.description}</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="flex items-center justify-between">
                <div className="flex items-center space-x-3">
                  <Avatar className="h-8 w-8">
                    <AvatarFallback>
                      {survey.author.username
                        .split(' ')
                        .map((n) => n[0])
                        .join('')}
                    </AvatarFallback>
                  </Avatar>
                  <div>
                    <p className="text-sm font-medium">
                      {survey.author.username}
                    </p>
                    <p className="text-xs text-gray-500">{survey.createdAt}</p>
                  </div>
                </div>
                <div className="text-right">
                  <div className="flex items-center text-sm text-gray-600 mb-2">
                    <Users className="h-4 w-4 mr-1" />
                    {survey.answers.toLocaleString()} respuestas
                  </div>
                  <Link href={`/survey/${survey.id}`}>
                    <Button size="sm">Participar</Button>
                  </Link>
                </div>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}

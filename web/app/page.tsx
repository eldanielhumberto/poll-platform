'use client';

import Link from 'next/link';
import {
  ArrowRight,
  BarChart3,
  Globe,
  Plus,
  Shield,
  TrendingUp,
  Users,
  Zap,
} from 'lucide-react';

import { useAuth } from '@/hooks/useAuth';

import { Button } from '../components/ui/button';
import {
  Card,
  CardDescription,
  CardHeader,
  CardTitle,
} from '../components/ui/card';

export default function Home() {
  const { user } = useAuth();

  return (
    <main className="bg-gradient-to-br from-blue-50 via-white to-purple-50">
      {/* Header */}
      <header className=" bg-white/80 backdrop-blur-sm fixed w-full top-0 z-50">
        <div className="container mx-auto px-4 py-4 flex items-center justify-between">
          <div className="flex items-center space-x-2">
            <BarChart3 className="h-8 w-8 text-blue-600" />
            <span className="text-2xl font-bold text-gray-900">
              Poll Platform
            </span>
          </div>
          {!user && (
            <nav className="hidden md:flex items-center space-x-8">
              <Link
                href="#features"
                className="text-gray-600 hover:text-gray-900 transition-colors"
              >
                Características
              </Link>
              <Link
                href="#how-it-works"
                className="text-gray-600 hover:text-gray-900 transition-colors"
              >
                Cómo funciona
              </Link>
            </nav>
          )}
          <div className="flex items-center space-x-4">
            {user ? (
              <>
                <Link
                  href="/dashboard"
                  className={`text-sm font-medium transition-colors hover:text-blue-600 `}
                >
                  <Button
                    size="sm"
                    variant="outline"
                    className="hidden sm:flex w-40"
                  >
                    Ir al dashboard
                  </Button>
                </Link>
                <Link href="/create">
                  <Button size="sm" className="hidden sm:flex w-40">
                    <Plus className="h-4 w-4 mr-2" />
                    Nueva Encuesta
                  </Button>
                </Link>
              </>
            ) : (
              <>
                <Link href="/auth/login">
                  <Button variant="ghost">Iniciar Sesión</Button>
                </Link>
                <Link href="/auth/register">
                  <Button>Registrarse</Button>
                </Link>
              </>
            )}
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="min-h-screen flex items-center justify-center">
        <div className="container mx-auto text-center">
          <h1 className="text-5xl md:text-6xl font-bold text-gray-900 mb-6">
            Crea encuestas
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-purple-600">
              {' '}
              increíbles
            </span>
          </h1>
          <p className="text-xl text-gray-600 mb-8 max-w-3xl mx-auto">
            Diseña, comparte y analiza encuestas de manera fácil y rápida. Obtén
            insights valiosos de tu audiencia con gráficos en tiempo real.
          </p>
          <div className="flex items-center flex-col sm:flex-row gap-4 justify-center">
            {!user && (
              <Link href="/auth/register">
                <Button size="lg" className="text-lg px-8 py-3">
                  Comenzar Gratis
                  <ArrowRight className="ml-2 h-5 w-5" />
                </Button>
              </Link>
            )}

            <Link href="/explore">
              <Button
                size="lg"
                variant="outline"
                className="text-lg px-8 py-3 bg-transparent"
              >
                Explorar Encuestas
              </Button>
            </Link>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-20 px-4 bg-white">
        <div className="container mx-auto">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-4">
              Todo lo que necesitas para crear encuestas exitosas
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Herramientas poderosas y fáciles de usar para crear, compartir y
              analizar encuestas
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <Zap className="h-12 w-12 text-blue-600 mb-4" />
                <CardTitle>Creación Rápida</CardTitle>
                <CardDescription>
                  Crea encuestas profesionales en minutos con nuestro editor
                  intuitivo
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <BarChart3 className="h-12 w-12 text-green-600 mb-4" />
                <CardTitle>Análisis en Tiempo Real</CardTitle>
                <CardDescription>
                  Visualiza resultados con gráficos interactivos que se
                  actualizan automáticamente
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <Users className="h-12 w-12 text-purple-600 mb-4" />
                <CardTitle>Comunidad Activa</CardTitle>
                <CardDescription>
                  Explora y participa en encuestas de otros usuarios de la
                  comunidad
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <Shield className="h-12 w-12 text-red-600 mb-4" />
                <CardTitle>Seguro y Privado</CardTitle>
                <CardDescription>
                  Tus datos están protegidos con los más altos estándares de
                  seguridad
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <Globe className="h-12 w-12 text-indigo-600 mb-4" />
                <CardTitle>Acceso Global</CardTitle>
                <CardDescription>
                  Comparte tus encuestas con cualquier persona, en cualquier
                  lugar del mundo
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-shadow">
              <CardHeader>
                <TrendingUp className="h-12 w-12 text-orange-600 mb-4" />
                <CardTitle>Insights Avanzados</CardTitle>
                <CardDescription>
                  Obtén análisis detallados y tendencias de las respuestas de
                  tus encuestas
                </CardDescription>
              </CardHeader>
            </Card>
          </div>
        </div>
      </section>

      {/* How it Works */}
      <section id="how-it-works" className="py-20 px-4 bg-gray-50">
        <div className="container mx-auto">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-4">
              Cómo funciona
            </h2>
            <p className="text-xl text-gray-600">
              Tres simples pasos para crear tu primera encuesta
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-8">
            <div className="text-center">
              <div className="w-16 h-16 bg-blue-600 text-white rounded-full flex items-center justify-center text-2xl font-bold mx-auto mb-4">
                1
              </div>
              <h3 className="text-xl font-semibold mb-2">Crea tu cuenta</h3>
              <p className="text-gray-600">
                Regístrate gratis y accede a todas las herramientas de creación
                de encuestas
              </p>
            </div>

            <div className="text-center">
              <div className="w-16 h-16 bg-green-600 text-white rounded-full flex items-center justify-center text-2xl font-bold mx-auto mb-4">
                2
              </div>
              <h3 className="text-xl font-semibold mb-2">Diseña tu encuesta</h3>
              <p className="text-gray-600">
                Usa nuestro editor intuitivo para crear preguntas y personalizar
                tu encuesta
              </p>
            </div>

            <div className="text-center">
              <div className="w-16 h-16 bg-purple-600 text-white rounded-full flex items-center justify-center text-2xl font-bold mx-auto mb-4">
                3
              </div>
              <h3 className="text-xl font-semibold mb-2">Analiza resultados</h3>
              <p className="text-gray-600">
                Comparte tu encuesta y observa los resultados en tiempo real con
                gráficos interactivos
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      {!user && (
        <section className="py-20 px-4 bg-gradient-to-r from-blue-600 to-purple-600">
          <div className="container mx-auto text-center flex flex-col items-center">
            <h2 className="text-4xl font-bold text-white mb-4">
              ¿Listo para comenzar?
            </h2>
            <p className="text-xl text-blue-100 mb-8 max-w-2xl mx-auto">
              Únete a miles de usuarios que ya están creando encuestas
              increíbles
            </p>
            <Link href="/auth/register">
              <Button
                size="lg"
                variant="secondary"
                className="text-lg px-8 py-3"
              >
                Crear Cuenta Gratis
                <ArrowRight className="ml-2 h-5 w-5" />
              </Button>
            </Link>
          </div>
        </section>
      )}

      {/* Footer */}
      <footer className="bg-gray-900 text-white py-12 px-4">
        <div className="container mx-auto">
          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-2">
              <BarChart3 className="h-8 w-8 text-blue-400" />
              <span className="text-2xl font-bold">Poll Platform</span>
            </div>
            <p className="text-gray-400">
              Nya studios © {new Date().getFullYear()}
            </p>
          </div>
        </div>
      </footer>
    </main>
  );
}

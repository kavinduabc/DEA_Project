'use client'

import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { BookOpen, Users, GraduationCap, TrendingUp, Zap, LayoutDashboard, LogIn, UserPlus } from 'lucide-react'

export default function Home() {
  return (
    <div className="min-h-screen bg-background">
      {/* Navigation */}
      <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 sticky top-0 z-50">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <div className="flex items-center gap-2">
              <GraduationCap className="h-8 w-8 text-primary" />
              <span className="text-2xl font-bold text-foreground">OpenTutor</span>
            </div>
            <div className="flex items-center gap-3">
              <Link href="/login">
                <Button variant="ghost" size="lg" className="gap-2">
                  <LogIn className="h-4 w-4" />
                  Log In
                </Button>
              </Link>
              <Link href="/signup">
                <Button size="lg" className="gap-2">
                  <UserPlus className="h-4 w-4" />
                  Sign Up
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="relative overflow-hidden bg-gradient-to-b from-orange-50/50 to-background dark:from-orange-950/10">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-20 sm:py-32">
          <div className="max-w-4xl mx-auto text-center">
            <h1 className="text-4xl sm:text-5xl lg:text-6xl font-bold text-foreground mb-6 leading-tight">
              Unlock Your Potential.{' '}
              <span className="text-primary">Teach and Learn</span>{' '}
              Without Limits.
            </h1>
            <p className="text-lg sm:text-xl text-muted-foreground mb-8 max-w-2xl mx-auto">
              Join a community where knowledge flows freely. Share your expertise or discover new passions with OpenTutor's open learning platform.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link href="/signup?role=student">
                <Button size="lg" className="text-base px-8 py-6 shadow-lg hover:shadow-xl transition-all">
                  Start Learning Today
                </Button>
              </Link>
              <Link href="/signup?role=teacher">
                <Button size="lg" variant="outline" className="text-base px-8 py-6">
                  Become a Teacher
                </Button>
              </Link>
            </div>
          </div>
        </div>
        <div className="absolute inset-0 -z-10 opacity-30">
          <div className="absolute top-20 left-10 w-72 h-72 bg-primary/20 rounded-full blur-3xl" />
          <div className="absolute bottom-20 right-10 w-96 h-96 bg-primary/10 rounded-full blur-3xl" />
        </div>
      </section>

      {/* Why OpenTutor Section */}
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
              Why OpenTutor?
            </h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              An open platform where anyone can be a teacher. No institutional walls, just pure knowledge sharing.
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-8 max-w-6xl mx-auto">
            <Card className="border-2 hover:border-primary transition-colors">
              <CardHeader>
                <div className="w-12 h-12 bg-primary/10 rounded-lg flex items-center justify-center mb-4">
                  <Zap className="h-6 w-6 text-primary" />
                </div>
                <CardTitle className="text-xl">No Barriers</CardTitle>
                <CardDescription>
                  Teach what you know, learn what you want. Our platform removes traditional institutional barriers to education.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-2 hover:border-primary transition-colors">
              <CardHeader>
                <div className="w-12 h-12 bg-primary/10 rounded-lg flex items-center justify-center mb-4">
                  <Users className="h-6 w-6 text-primary" />
                </div>
                <CardTitle className="text-xl">Community Driven</CardTitle>
                <CardDescription>
                  Connect with passionate educators and eager learners from around the world in one unified platform.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card className="border-2 hover:border-primary transition-colors">
              <CardHeader>
                <div className="w-12 h-12 bg-primary/10 rounded-lg flex items-center justify-center mb-4">
                  <TrendingUp className="h-6 w-6 text-primary" />
                </div>
                <CardTitle className="text-xl">Grow Together</CardTitle>
                <CardDescription>
                  Track progress, earn recognition, and continuously improve your teaching or learning journey.
                </CardDescription>
              </CardHeader>
            </Card>
          </div>
        </div>
      </section>

      {/* Features for Teachers */}
      <section className="py-20 bg-muted/30">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-16">
              <div className="inline-flex items-center gap-2 bg-primary/10 text-primary px-4 py-2 rounded-full text-sm font-medium mb-4">
                <BookOpen className="h-4 w-4" />
                For Teachers
              </div>
              <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
                Empower Your Teaching
              </h2>
              <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
                Everything you need to create impactful learning experiences.
              </p>
            </div>

            <div className="grid md:grid-cols-3 gap-6">
              <Card className="bg-gradient-to-br from-orange-50 to-white dark:from-orange-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-primary rounded-xl flex items-center justify-center mb-4">
                    <LayoutDashboard className="h-7 w-7 text-primary-foreground" />
                  </div>
                  <CardTitle className="text-xl">Create Virtual Classrooms</CardTitle>
                  <CardDescription className="text-base">
                    Set up your own virtual classroom in seconds. Intuitive tools make it easy to start teaching immediately.
                  </CardDescription>
                </CardHeader>
              </Card>

              <Card className="bg-gradient-to-br from-orange-50 to-white dark:from-orange-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-primary rounded-xl flex items-center justify-center mb-4">
                    <BookOpen className="h-7 w-7 text-primary-foreground" />
                  </div>
                  <CardTitle className="text-xl">Organize Content with Ease</CardTitle>
                  <CardDescription className="text-base">
                    Structure your lessons with modules, upload resources, and create quizzes. Everything organized your way.
                  </CardDescription>
                </CardHeader>
              </Card>

              <Card className="bg-gradient-to-br from-orange-50 to-white dark:from-orange-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-primary rounded-xl flex items-center justify-center mb-4">
                    <TrendingUp className="h-7 w-7 text-primary-foreground" />
                  </div>
                  <CardTitle className="text-xl">Built In Grading Tools</CardTitle>
                  <CardDescription className="text-base">
                    Streamline assessments with automatic quiz grading and easy assignment feedback management.
                  </CardDescription>
                </CardHeader>
              </Card>
            </div>
          </div>
        </div>
      </section>

      {/* Features for Students */}
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-16">
              <div className="inline-flex items-center gap-2 bg-primary/10 text-primary px-4 py-2 rounded-full text-sm font-medium mb-4">
                <GraduationCap className="h-4 w-4" />
                For Students
              </div>
              <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
                Accelerate Your Learning
              </h2>
              <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
                Discover new subjects and master skills at your own pace.
              </p>
            </div>

            <div className="grid md:grid-cols-3 gap-6">
              <Card className="bg-gradient-to-br from-slate-50 to-white dark:from-slate-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-slate-800 dark:bg-slate-200 rounded-xl flex items-center justify-center mb-4">
                    <Users className="h-7 w-7 text-slate-100 dark:text-slate-800" />
                  </div>
                  <CardTitle className="text-xl">Learn from Experts</CardTitle>
                  <CardDescription className="text-base">
                    Access a wide variety of courses created by independent experts passionate about their subjects.
                  </CardDescription>
                </CardHeader>
              </Card>

              <Card className="bg-gradient-to-br from-slate-50 to-white dark:from-slate-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-slate-800 dark:bg-slate-200 rounded-xl flex items-center justify-center mb-4">
                    <TrendingUp className="h-7 w-7 text-slate-100 dark:text-slate-800" />
                  </div>
                  <CardTitle className="text-xl">Track Your Progress</CardTitle>
                  <CardDescription className="text-base">
                    Visual progress indicators help you stay motivated and see how far you've come in each course.
                  </CardDescription>
                </CardHeader>
              </Card>

              <Card className="bg-gradient-to-br from-slate-50 to-white dark:from-slate-950/20 dark:to-card">
                <CardHeader>
                  <div className="w-14 h-14 bg-slate-800 dark:bg-slate-200 rounded-xl flex items-center justify-center mb-4">
                    <Zap className="h-7 w-7 text-slate-100 dark:text-slate-800" />
                  </div>
                  <CardTitle className="text-xl">Join Communities</CardTitle>
                  <CardDescription className="text-base">
                    Connect with fellow learners and teachers through discussions, forums, and collaborative activities.
                  </CardDescription>
                </CardHeader>
              </Card>
            </div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-gradient-to-br from-primary via-orange-500 to-orange-600 text-white">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-4xl mx-auto text-center">
            <h2 className="text-3xl sm:text-4xl font-bold mb-6">
              Ready to Start Your Journey?
            </h2>
            <p className="text-xl opacity-90 mb-8">
              Join thousands of learners and teachers already using OpenTutor to transform education.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link href="/signup?role=student">
                <Button size="lg" variant="secondary" className="text-base px-8 py-6">
                  Sign Up as Student
                </Button>
              </Link>
              <Link href="/signup?role=teacher">
                <Button size="lg" variant="outline" className="text-base px-8 py-6 bg-white/10 hover:bg-white/20 text-white border-white/30">
                  Sign Up as Teacher
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-muted py-12">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto">
            <div className="flex flex-col md:flex-row justify-between items-center gap-6">
              <div className="flex items-center gap-2">
                <GraduationCap className="h-6 w-6 text-primary" />
                <span className="text-xl font-bold">OpenTutor</span>
              </div>
              <div className="flex gap-6 text-sm text-muted-foreground">
                <Link href="#" className="hover:text-primary transition-colors">About</Link>
                <Link href="#" className="hover:text-primary transition-colors">Privacy</Link>
                <Link href="#" className="hover:text-primary transition-colors">Terms</Link>
                <Link href="#" className="hover:text-primary transition-colors">Contact</Link>
              </div>
            </div>
            <div className="mt-8 pt-8 border-t text-center text-sm text-muted-foreground">
              © 2024 OpenTutor. All rights reserved.
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}

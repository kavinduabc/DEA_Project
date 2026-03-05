'use client'

import { useState } from 'react'
import Link from 'next/link'
import { useRouter } from 'next/navigation'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { GraduationCap, Mail, Lock, ArrowLeft, CheckCircle2 } from 'lucide-react'

export default function LoginPage() {
  const router = useRouter()
  const [role, setRole] = useState<'student' | 'teacher'>('student')

  // Matches Profile Service — Login User (POST /api/auth/login)
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  })

  // TODO: POST /api/auth/login → { email, password }
  // Response: { token, userId, role }
  // Store token in auth context / cookie, then redirect based on role
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log('POST /api/auth/login', { email: formData.email, password: formData.password })
    if (role === 'student') {
      router.push('/student-dashboard')
    } else {
      router.push('/teacher-dashboard')
    }
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50/50 via-background to-slate-50/50 dark:from-orange-950/10 dark:via-background dark:to-slate-950/10">
      {/* Header */}
      <div className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <Link href="/" className="flex items-center gap-2">
              <GraduationCap className="h-8 w-8 text-primary" />
              <span className="text-2xl font-bold text-foreground">OpenTutor</span>
            </Link>
            <Link href="/signup">
              <Button variant="ghost" size="lg">
                New to OpenTutor? Sign Up
              </Button>
            </Link>
          </div>
        </div>
      </div>

      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="max-w-md mx-auto">
          <div className="text-center mb-8">
            <Link
              href="/"
              className="inline-flex items-center gap-2 text-muted-foreground hover:text-foreground mb-4 transition-colors"
            >
              <ArrowLeft className="h-4 w-4" />
              Back to Home
            </Link>
            <h1 className="text-3xl font-bold text-foreground mb-2">Welcome Back</h1>
            <p className="text-muted-foreground">Sign in to continue your journey</p>
          </div>

          {/* Role Selection — used only for client-side redirect after login */}
          {/* <div className="grid grid-cols-2 gap-4 mb-6">
            {(['student', 'teacher'] as const).map((r) => (
              <Card
                key={r}
                className={`cursor-pointer transition-all border-2 ${
                  role === r
                    ? 'border-primary bg-primary/5 shadow-md'
                    : 'border-border hover:border-primary/50'
                }`}
                onClick={() => setRole(r)}
              >
                <CardContent className="pt-6">
                  <div className="flex flex-col items-center gap-2 text-center">
                    <div
                      className={`w-12 h-12 rounded-full flex items-center justify-center ${
                        role === r ? 'bg-primary' : 'bg-muted'
                      }`}
                    >
                      {role === r ? (
                        <CheckCircle2 className="h-6 w-6 text-primary-foreground" />
                      ) : (
                        <span className="text-2xl">{r === 'student' ? '👨‍🎓' : '👨‍🏫'}</span>
                      )}
                    </div>
                    <span
                      className={`font-semibold capitalize ${
                        role === r ? 'text-primary' : 'text-foreground'
                      }`}
                    >
                      {r}
                    </span>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div> */}

          {/* Login Form — Profile Service: POST /api/auth/login */}
          <Card className="shadow-lg">
            {/* <CardHeader>
              <CardTitle className="text-xl">
                Log in as {role === 'student' ? 'Student' : 'Teacher'}
              </CardTitle>
              <CardDescription>
                Enter your credentials to access your {role === 'student' ? 'courses' : 'classrooms'}
              </CardDescription>
            </CardHeader> */}
            <CardContent>
              <form onSubmit={handleSubmit} className="space-y-4">
                {/* email */}
                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <div className="relative">
                    <Mail className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="email"
                      type="email"
                      placeholder="john@example.com"
                      className="pl-9"
                      value={formData.email}
                      onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                      required
                    />
                  </div>
                </div>

                {/* password */}
                <div className="space-y-2">
                  <Label htmlFor="password">Password</Label>
                  <div className="relative">
                    <Lock className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="password"
                      type="password"
                      placeholder="••••••••"
                      className="pl-9"
                      value={formData.password}
                      onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                      required
                    />
                  </div>
                </div>

                <Button type="submit" className="w-full" size="lg">
                  Log In
                </Button>
              </form>
            </CardContent>
          </Card>

          <div className="mt-6 text-center">
            <p className="text-muted-foreground">
              Don&apos;t have an account?{' '}
              <Link href="/signup" className="text-primary font-medium hover:underline">
                Sign up
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}

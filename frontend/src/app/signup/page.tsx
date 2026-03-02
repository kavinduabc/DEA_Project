'use client'

import { useState } from 'react'
import Link from 'next/link'
import { useRouter, useSearchParams } from 'next/navigation'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { GraduationCap, User, Mail, Lock, ArrowLeft, UserCheck, GraduationCap as TeacherIcon } from 'lucide-react'

export default function SignUpPage() {
  const router = useRouter()
  const searchParams = useSearchParams()
  const roleFromUrl = searchParams.get('role')
  
  const [role, setRole] = useState<'student' | 'teacher'>(roleFromUrl === 'teacher' ? 'teacher' : 'student')
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: ''
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    // TODO: Implement actual signup logic
    console.log('Signup:', { ...formData, role })
    // Redirect to appropriate dashboard
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
            <Link href="/login">
              <Button variant="ghost" size="lg" className="gap-2">
                Already have an account? Log In
              </Button>
            </Link>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="max-w-md mx-auto">
          <div className="text-center mb-8">
            <Link href="/" className="inline-flex items-center gap-2 text-muted-foreground hover:text-foreground mb-4 transition-colors">
              <ArrowLeft className="h-4 w-4" />
              Back to Home
            </Link>
            <h1 className="text-3xl font-bold text-foreground mb-2">Create Account</h1>
            <p className="text-muted-foreground">Join OpenTutor and start your journey</p>
          </div>

          {/* Role Selection */}
          <div className="grid grid-cols-2 gap-4 mb-6">
            <Card
              className={`cursor-pointer transition-all border-2 ${
                role === 'student'
                  ? 'border-primary bg-primary/5 shadow-md'
                  : 'border-border hover:border-primary/50'
              }`}
              onClick={() => setRole('student')}
            >
              <CardContent className="pt-6">
                <div className="flex flex-col items-center gap-2 text-center">
                  <div className={`w-12 h-12 rounded-full flex items-center justify-center ${
                    role === 'student' ? 'bg-primary' : 'bg-muted'
                  }`}>
                    <UserCheck className={`h-6 w-6 ${
                      role === 'student' ? 'text-primary-foreground' : 'text-muted-foreground'
                    }`} />
                  </div>
                  <span className={`font-semibold ${
                    role === 'student' ? 'text-primary' : 'text-foreground'
                  }`}>Student</span>
                </div>
              </CardContent>
            </Card>

            <Card
              className={`cursor-pointer transition-all border-2 ${
                role === 'teacher'
                  ? 'border-primary bg-primary/5 shadow-md'
                  : 'border-border hover:border-primary/50'
              }`}
              onClick={() => setRole('teacher')}
            >
              <CardContent className="pt-6">
                <div className="flex flex-col items-center gap-2 text-center">
                  <div className={`w-12 h-12 rounded-full flex items-center justify-center ${
                    role === 'teacher' ? 'bg-primary' : 'bg-muted'
                  }`}>
                    <TeacherIcon className={`h-6 w-6 ${
                      role === 'teacher' ? 'text-primary-foreground' : 'text-muted-foreground'
                    }`} />
                  </div>
                  <span className={`font-semibold ${
                    role === 'teacher' ? 'text-primary' : 'text-foreground'
                  }`}>Teacher</span>
                </div>
              </CardContent>
            </Card>
          </div>

          {/* Sign Up Form */}
          <Card className="shadow-lg">
            <CardHeader>
              <CardTitle className="text-xl">
                Sign up as {role === 'student' ? 'Student' : 'Teacher'}
              </CardTitle>
              <CardDescription>
                {role === 'student'
                  ? 'Start learning from experts around the world'
                  : 'Share your knowledge and create impactful courses'
                }
              </CardDescription>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit} className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="firstName">First Name</Label>
                    <div className="relative">
                      <User className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                      <Input
                        id="firstName"
                        placeholder="John"
                        className="pl-9"
                        value={formData.firstName}
                        onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                        required
                      />
                    </div>
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="lastName">Last Name</Label>
                    <div className="relative">
                      <User className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                      <Input
                        id="lastName"
                        placeholder="Doe"
                        className="pl-9"
                        value={formData.lastName}
                        onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
                        required
                      />
                    </div>
                  </div>
                </div>

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

                <div className="space-y-2">
                  <Label htmlFor="confirmPassword">Confirm Password</Label>
                  <div className="relative">
                    <Lock className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="confirmPassword"
                      type="password"
                      placeholder="••••••••"
                      className="pl-9"
                      value={formData.confirmPassword}
                      onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
                      required
                    />
                  </div>
                </div>

                <Button type="submit" className="w-full" size="lg">
                  Create Account
                </Button>

                <div className="text-center text-sm text-muted-foreground">
                  By creating an account, you agree to our{' '}
                  <Link href="#" className="text-primary hover:underline">Terms of Service</Link>
                  {' '}and{' '}
                  <Link href="#" className="text-primary hover:underline">Privacy Policy</Link>
                </div>
              </form>
            </CardContent>
          </Card>

          <div className="mt-6 text-center">
            <p className="text-muted-foreground">
              Already have an account?{' '}
              <Link href="/login" className="text-primary font-medium hover:underline">
                Log in
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}

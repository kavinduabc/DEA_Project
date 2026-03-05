'use client'

import { useState } from 'react'
import Link from 'next/link'
import { useRouter, useSearchParams } from 'next/navigation'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { GraduationCap, User, Mail, Lock, ArrowLeft, UserCheck } from 'lucide-react'

export default function SignUpPage() {
  const router = useRouter()
  const searchParams = useSearchParams()
  const roleFromUrl = searchParams.get('role')

  // Matches Profile Service — Assign Role (student / teacher)
  const [role, setRole] = useState<'student' | 'teacher'>(
    roleFromUrl === 'teacher' ? 'teacher' : 'student'
  )

  // Matches Profile Service — Register User + Update Profile
  // POST /api/auth/register → { name, email, password, role }
  const [formData, setFormData] = useState({
    name: '',        // stored in profiles.name
    email: '',       // stored in users.email
    password: '',    // stored as hashed in users.password
    confirmPassword: '',
  })

  const passwordsMatch = formData.password === formData.confirmPassword
  const isValid = formData.name && formData.email && formData.password && passwordsMatch

  // TODO: POST /api/auth/register → { name, email, password, role }
  // Response: { userId, token }
  // Then redirect to appropriate dashboard
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (!isValid) return
    console.log('POST /api/auth/register', {
      name: formData.name,
      email: formData.email,
      password: formData.password,
      role,
    })
    router.push(role === 'student' ? '/student-dashboard' : '/teacher-dashboard')
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
              <Button variant="ghost" size="lg">
                Already have an account? Log In
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
            <h1 className="text-3xl font-bold text-foreground mb-2">Create Account</h1>
            <p className="text-muted-foreground">Join OpenTutor and start your journey</p>
          </div>

          {/* Role Selection — Profile Service: Assign Role */}
          <div className="grid grid-cols-2 gap-4 mb-6">
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
                      <UserCheck
                        className={`h-6 w-6 ${
                          role === r ? 'text-primary-foreground' : 'text-muted-foreground'
                        }`}
                      />
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
          </div>

          {/* Register Form — Profile Service: POST /api/auth/register */}
          <Card className="shadow-lg">
            <CardHeader>
              <CardTitle className="text-xl">
                Sign up as {role === 'student' ? 'Student' : 'Teacher'}
              </CardTitle>
              <CardDescription>
                {role === 'student'
                  ? 'Start learning from experts around the world'
                  : 'Share your knowledge and create impactful courses'}
              </CardDescription>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit} className="space-y-4">
                {/* name — stored in profiles.name */}
                <div className="space-y-2">
                  <Label htmlFor="name">Full Name</Label>
                  <div className="relative">
                    <User className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="name"
                      placeholder="John Doe"
                      className="pl-9"
                      value={formData.name}
                      onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                      required
                    />
                  </div>
                </div>

                {/* email — stored in users.email */}
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

                {/* password — hashed by Profile Service */}
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

                {/* confirmPassword — client-side validation only, not sent to API */}
                <div className="space-y-2">
                  <Label htmlFor="confirmPassword">Confirm Password</Label>
                  <div className="relative">
                    <Lock className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="confirmPassword"
                      type="password"
                      placeholder="••••••••"
                      className={`pl-9 ${
                        formData.confirmPassword && !passwordsMatch ? 'border-destructive' : ''
                      }`}
                      value={formData.confirmPassword}
                      onChange={(e) =>
                        setFormData({ ...formData, confirmPassword: e.target.value })
                      }
                      required
                    />
                  </div>
                  {formData.confirmPassword && !passwordsMatch && (
                    <p className="text-xs text-destructive">Passwords do not match</p>
                  )}
                </div>

                <Button type="submit" className="w-full" size="lg" disabled={!isValid}>
                  Create Account
                </Button>

                <p className="text-center text-sm text-muted-foreground">
                  By creating an account, you agree to our{' '}
                  <Link href="#" className="text-primary hover:underline">
                    Terms of Service
                  </Link>{' '}
                  and{' '}
                  <Link href="#" className="text-primary hover:underline">
                    Privacy Policy
                  </Link>
                </p>
              </form>
            </CardContent>
          </Card>

          {/* {Login} */}

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

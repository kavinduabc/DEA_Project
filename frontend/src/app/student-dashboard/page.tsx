
'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Badge } from '@/components/ui/badge'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import {
  GraduationCap,
  Plus,
  BookOpen,
  Power,
  LogOut,
} from 'lucide-react'

// Matches ClassroomResponseDTO from Classroom Service
interface Classroom {
  id: number
  teacherId: string
  title: string
  subject: string
  bannerImage: string | null
  inviteCode: string | null
  isActive: boolean
}

export default function StudentDashboard() {
  const [showJoinModal, setShowJoinModal] = useState(false)
  const [showLogoutModal, setShowLogoutModal] = useState(false)
  const [inviteCode, setInviteCode] = useState('')
  const [verifiedClassrooms, setVerifiedClassrooms] = useState<Classroom[]>([])
  const [verifyError, setVerifyError] = useState('')

  // TODO: Replace with GET /api/enrollments/student/{studentId}
  // then GET /api/classrooms/{id} for each enrolled classroom
  const enrolledClassrooms: Classroom[] = [
    {
      id: 1,
      teacherId: 'teacher-uuid-1',
      title: 'Advanced Java Programming',
      subject: 'Programming',
      bannerImage: null,
      inviteCode: 'JVA729',
      isActive: true,
    },
    {
      id: 2,
      teacherId: 'teacher-uuid-2',
      title: 'Data Structures & Algorithms',
      subject: 'Computer Science',
      bannerImage: null,
      inviteCode: 'DSA483',
      isActive: true,
    },
  ]

  // TODO: Replace with GET /api/classrooms — all active classrooms not yet enrolled in
  const availableClassrooms: Classroom[] = [
    {
      id: 3,
      teacherId: 'teacher-uuid-3',
      title: 'Web Development Bootcamp',
      subject: 'Web Development',
      bannerImage: null,
      inviteCode: 'WEB956',
      isActive: true,
    },
    {
      id: 4,
      teacherId: 'teacher-uuid-4',
      title: 'Python for Beginners',
      subject: 'Programming',
      bannerImage: null,
      inviteCode: 'PYT001',
      isActive: true,
    },
    {
      id: 5,
      teacherId: 'teacher-uuid-5',
      title: 'UI/UX Design Fundamentals',
      subject: 'Design',
      bannerImage: null,
      inviteCode: 'UIX202',
      isActive: true,
    },
  ]

  // Profile Service — clear session and redirect to login
  // TODO: POST /api/auth/logout (invalidate token), then redirect to /login
  const handleLogout = () => {
    console.log('POST /api/auth/logout')
    setShowLogoutModal(false)
    window.location.href = '/login'
  }

  
  // GET /api/classrooms/verify/{inviteCode}
  const handleVerifyCode = () => {
    console.log('GET /api/classrooms/verify/' + inviteCode)
    setVerifyError('')
    setVerifiedClassrooms([])
    // TODO: on success → setVerifiedClassrooms(data)
    // TODO: on empty  → setVerifyError('No classroom found with this invite code.')
  }

  // POST /api/enrollments → { studentId, classroomId }  (Enrollment Service)
  const handleJoinClass = (classroomId: number) => {
    console.log('POST /api/enrollments', { classroomId })
    setShowJoinModal(false)
    setInviteCode('')
    setVerifiedClassrooms([])
  }

  return (
    <div className="min-h-screen bg-background">

      {/* Navigation */}
      <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 sticky top-0 z-40">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <Link href="/" className="flex items-center gap-2">
              <GraduationCap className="h-8 w-8 text-primary" />
              <span className="text-2xl font-bold text-foreground">OpenTutor</span>
            </Link>
            <div className="flex items-center gap-3">

              {/* Join Class — Enrollment Service */}
              <Dialog open={showJoinModal} onOpenChange={setShowJoinModal}>
                <DialogTrigger asChild>
                  <Button size="lg" className="gap-2">
                    <Plus className="h-4 w-4" />
                    Join Class
                  </Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-md">
                  <DialogHeader>
                    <DialogTitle>Join a Classroom</DialogTitle>
                    <DialogDescription>
                      Enter the invite code provided by your teacher to find and join their classroom.
                    </DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <label htmlFor="inviteCode" className="text-sm font-medium">
                        Invite Code
                      </label>
                      <div className="flex gap-2">
                        <Input
                          id="inviteCode"
                          placeholder="e.g., ABC123"
                          value={inviteCode}
                          onChange={(e) => {
                            setInviteCode(e.target.value.toUpperCase())
                            setVerifiedClassrooms([])
                            setVerifyError('')
                          }}
                          maxLength={10}
                          className="text-center text-xl tracking-widest uppercase"
                        />
                        {/* Classroom Service: GET /api/classrooms/verify/{inviteCode} */}
                        <Button
                          variant="outline"
                          onClick={handleVerifyCode}
                          disabled={inviteCode.length < 3}
                        >
                          Search
                        </Button>
                      </div>
                    </div>

                    {verifiedClassrooms.length > 0 && (
                      <div className="space-y-2">
                        {verifiedClassrooms.map((c) => (
                          <div key={c.id} className="flex items-center justify-between p-3 border rounded-lg">
                            <div>
                              <div className="font-semibold">{c.title}</div>
                              <div className="text-sm text-muted-foreground">{c.subject}</div>
                            </div>
                            {/* Enrollment Service: POST /api/enrollments */}
                            <Button size="sm" onClick={() => handleJoinClass(c.id)}>
                              Join
                            </Button>
                          </div>
                        ))}
                      </div>
                    )}

                    {verifyError && (
                      <p className="text-sm text-destructive">{verifyError}</p>
                    )}

                    <Button
                      variant="outline"
                      className="w-full"
                      onClick={() => {
                        setShowJoinModal(false)
                        setInviteCode('')
                        setVerifiedClassrooms([])
                        setVerifyError('')
                      }}
                    >
                      Cancel
                    </Button>
                  </div>
                </DialogContent>
              </Dialog>

              {/* Logout — Profile Service */}
              <Button
                variant="ghost"
                size="sm"
                className="gap-2 text-muted-foreground hover:text-destructive"
                onClick={() => setShowLogoutModal(true)}
              >
                <LogOut className="h-4 w-4" />
                Logout
              </Button>

              {/* Logout Confirmation Dialog */}
              <Dialog open={showLogoutModal} onOpenChange={setShowLogoutModal}>
                <DialogContent className="sm:max-w-sm">
                  <DialogHeader>
                    <DialogTitle>Log out</DialogTitle>
                    <DialogDescription>
                      Are you sure you want to log out of OpenTutor?
                    </DialogDescription>
                  </DialogHeader>
                  <div className="flex gap-3 pt-2">
                    <Button
                      variant="outline"
                      className="flex-1"
                      onClick={() => setShowLogoutModal(false)}
                    >
                      Cancel
                    </Button>
                    <Button
                      variant="destructive"
                      className="flex-1"
                      onClick={handleLogout}
                    >
                      Log Out
                    </Button>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
          </div>
        </div>
      </nav>

      <main className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">

        {/* Welcome */}
        <div className="mb-8">
          <h1 className="text-3xl sm:text-4xl font-bold text-foreground mb-2">
            Welcome back, Alex! 👋
          </h1>
          <p className="text-muted-foreground text-lg">Your enrolled classrooms</p>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-8">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Enrolled Classrooms
              </CardTitle>
              <BookOpen className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{enrolledClassrooms.length}</div>
              <p className="text-xs text-muted-foreground mt-1">Active enrollments</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Active Classrooms
              </CardTitle>
              <Power className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">
                {enrolledClassrooms.filter((c) => c.isActive).length}
              </div>
              <p className="text-xs text-muted-foreground mt-1">Currently open</p>
            </CardContent>
          </Card>
        </div>

        {/* ── My Classrooms — Enrollment Service ── */}
        <h2 className="text-2xl font-bold mb-4">My Classrooms</h2>
        {enrolledClassrooms.length === 0 ? (
          <Card className="text-center py-12 mb-10">
            <CardContent>
              <BookOpen className="h-12 w-12 mx-auto text-muted-foreground mb-4" />
              <p className="text-muted-foreground">
                You haven&apos;t joined any classrooms yet. Use an invite code to join one!
              </p>
            </CardContent>
          </Card>
        ) : (
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6 mb-10">
            {enrolledClassrooms.map((classroom) => (
              <Card
                key={classroom.id}
                className="overflow-hidden hover:shadow-lg transition-shadow group"
              >
                <Link href={`/student-dashboard/class/${classroom.id}`}>
                  {classroom.bannerImage && (
                    <div
                      className="h-28 bg-cover bg-center"
                      style={{ backgroundImage: `url(${classroom.bannerImage})` }}
                    />
                  )}
                  <CardHeader>
                    <div className="flex items-start justify-between mb-2">
                      <Badge variant="outline">{classroom.subject}</Badge>
                      <Badge variant={classroom.isActive ? 'default' : 'secondary'}>
                        {classroom.isActive ? 'Active' : 'Inactive'}
                      </Badge>
                    </div>
                    <CardTitle className="text-xl group-hover:text-primary transition-colors">
                      {classroom.title}
                    </CardTitle>
                  </CardHeader>
                  <CardContent>
                    <p className="text-sm text-muted-foreground">
                      Tap to view classroom details
                    </p>
                  </CardContent>
                </Link>
              </Card>
            ))}
          </div>
        )}

        {/* ── Available Classrooms — GET /api/classrooms (all active, not yet enrolled) ── */}
        <h2 className="text-2xl font-bold mb-2">Available Classrooms</h2>
        <p className="text-muted-foreground mb-4">
          Active classrooms you can join with an invite code from the teacher.
        </p>
        {availableClassrooms.length === 0 ? (
          <Card className="text-center py-12">
            <CardContent>
              <BookOpen className="h-12 w-12 mx-auto text-muted-foreground mb-4" />
              <p className="text-muted-foreground">No available classrooms at the moment.</p>
            </CardContent>
          </Card>
        ) : (
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {availableClassrooms.map((classroom) => (
              <Card key={classroom.id} className="overflow-hidden hover:shadow-lg transition-shadow">
                {classroom.bannerImage && (
                  <div
                    className="h-28 bg-cover bg-center"
                    style={{ backgroundImage: `url(${classroom.bannerImage})` }}
                  />
                )}
                <CardHeader>
                  <div className="flex items-start justify-between mb-2">
                    <Badge variant="outline">{classroom.subject}</Badge>
                    <Badge variant="default">Active</Badge>
                  </div>
                  <CardTitle className="text-xl">{classroom.title}</CardTitle>
                  <CardDescription>Ask your teacher for the invite code to join.</CardDescription>
                </CardHeader>
                <CardFooter>
                  {/* Enrollment Service: opens Join modal pre-filled */}
                  <Button
                    variant="outline"
                    className="w-full gap-2"
                    onClick={() => setShowJoinModal(true)}
                  >
                    <Plus className="h-4 w-4" />
                    Join with Invite Code
                  </Button>
                </CardFooter>
              </Card>
            ))}
          </div>
        )}

      </main>
    </div>
  )
}
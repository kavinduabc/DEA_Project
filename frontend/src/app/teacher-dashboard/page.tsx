'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import {
  GraduationCap,
  Plus,
  BookOpen,
  Copy,
  Check,
  LogOut,
  Power,
  RefreshCw,
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

// Matches ClassroomRequestDTO from Classroom Service
interface NewClassroomForm {
  title: string
  subject: string
  bannerImage: File | null
  inviteCode: string
}

export default function TeacherDashboard() {
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [showLogoutModal, setShowLogoutModal] = useState(false)
  const [copiedCode, setCopiedCode] = useState<string | null>(null)
  const [bannerPreview, setBannerPreview] = useState<string | null>(null)
  const [newClass, setNewClass] = useState<NewClassroomForm>({
    title: '',
    subject: '',
    bannerImage: null,
    inviteCode: '',
  })

  // TODO: Replace with GET /api/classrooms/teacher/{teacherId}
  const classrooms: Classroom[] = [
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
      teacherId: 'teacher-uuid-1',
      title: 'Data Structures & Algorithms',
      subject: 'Computer Science',
      bannerImage: null,
      inviteCode: 'DSA483',
      isActive: true,
    },
    {
      id: 3,
      teacherId: 'teacher-uuid-1',
      title: 'Web Development Bootcamp',
      subject: 'Web Development',
      bannerImage: null,
      inviteCode: 'WEB956',
      isActive: false,
    },
  ]

  // TODO: Wire to POST /api/classrooms
  // Profile Service — clear session and redirect to login
  // TODO: POST /api/auth/logout (invalidate token), then redirect to /login
  const handleLogout = () => {
    console.log('POST /api/auth/logout')
    setShowLogoutModal(false)
    window.location.href = '/login'
  }

  const handleCreateClass = () => {
    // TODO: Send as multipart/form-data so bannerImage file is included
    const formData = new FormData()
    formData.append('teacherId', 'teacher-uuid-1') // from auth context
    formData.append('title', newClass.title)
    formData.append('subject', newClass.subject)
    if (newClass.bannerImage) formData.append('bannerImage', newClass.bannerImage)
    if (newClass.inviteCode) formData.append('inviteCode', newClass.inviteCode)
    formData.append('isActive', 'true')
    console.log('POST /api/classrooms (multipart/form-data)', Object.fromEntries(formData))
    setShowCreateModal(false)
    setNewClass({ title: '', subject: '', bannerImage: null, inviteCode: '' })
    setBannerPreview(null)
  }

  const handleBannerChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0] ?? null
    setNewClass({ ...newClass, bannerImage: file })
    if (file) {
      setBannerPreview(URL.createObjectURL(file))
    } else {
      setBannerPreview(null)
    }
  }

  const generateInviteCode = () => {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'
    const code = Array.from({ length: 6 }, () => chars[Math.floor(Math.random() * chars.length)]).join('')
    setNewClass((prev) => ({ ...prev, inviteCode: code }))
  }

  const copyInviteCode = (code: string) => {
    navigator.clipboard.writeText(code)
    setCopiedCode(code)
    setTimeout(() => setCopiedCode(null), 2000)
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
        {/* Welcome Section */}
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-8">
          <div>
            <h1 className="text-3xl sm:text-4xl font-bold text-foreground mb-2">
              Welcome back, Sarah! 👋
            </h1>
            <p className="text-muted-foreground text-lg">
              Manage your classrooms
            </p>
          </div>

          {/* Create Classroom — POST /api/classrooms */}
          <Dialog open={showCreateModal} onOpenChange={setShowCreateModal}>
            <DialogTrigger asChild>
              <Button size="lg" className="gap-2 shadow-lg">
                <Plus className="h-5 w-5" />
                Create New Classroom
              </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-lg">
              <DialogHeader>
                <DialogTitle>Create New Classroom</DialogTitle>
                <DialogDescription>
                  Fill in the details to set up a new classroom.
                </DialogDescription>
              </DialogHeader>
              <div className="space-y-4 py-4">
                {/* title — required */}
                <div className="space-y-2">
                  <Label htmlFor="title">
                    Class Title <span className="text-destructive">*</span>
                  </Label>
                  <Input
                    id="title"
                    placeholder="e.g., Advanced Java Programming"
                    value={newClass.title}
                    onChange={(e) => setNewClass({ ...newClass, title: e.target.value })}
                  />
                </div>

                {/* subject — required */}
                <div className="space-y-2">
                  <Label htmlFor="subject">
                    Subject <span className="text-destructive">*</span>
                  </Label>
                  <Input
                    id="subject"
                    placeholder="e.g., Programming"
                    value={newClass.subject}
                    onChange={(e) => setNewClass({ ...newClass, subject: e.target.value })}
                  />
                </div>

                {/* bannerImage — optional file upload */}
                <div className="space-y-2">
                  <Label htmlFor="bannerImage">Banner Image (optional)</Label>
                  {bannerPreview && (
                    <div className="relative rounded-lg overflow-hidden h-28 bg-muted">
                      <img
                        src={bannerPreview}
                        alt="Banner preview"
                        className="w-full h-full object-cover"
                      />
                      <button
                        type="button"
                        className="absolute top-2 right-2 bg-black/50 hover:bg-black/70 text-white rounded-full p-1 transition-colors"
                        onClick={() => {
                          setNewClass({ ...newClass, bannerImage: null })
                          setBannerPreview(null)
                        }}
                      >
                        ✕
                      </button>
                    </div>
                  )}
                  <label
                    htmlFor="bannerImage"
                    className="flex flex-col items-center justify-center w-full h-24 border-2 border-dashed border-muted-foreground/30 rounded-lg cursor-pointer hover:border-primary hover:bg-muted/30 transition-colors"
                  >
                    <div className="flex flex-col items-center gap-1 text-muted-foreground">
                      <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" /></svg>
                      <span className="text-sm">{bannerPreview ? 'Replace image' : 'Click to upload image'}</span>
                      <span className="text-xs">PNG, JPG, WEBP up to 5MB</span>
                    </div>
                    <Input
                      id="bannerImage"
                      type="file"
                      accept="image/png,image/jpeg,image/webp"
                      className="hidden"
                      onChange={handleBannerChange}
                    />
                  </label>
                </div>

                {/* inviteCode — manual entry or auto-generated */}
                <div className="space-y-2">
                  <Label htmlFor="inviteCode">Invite Code</Label>
                  <div className="flex gap-2">
                    <Input
                      id="inviteCode"
                      placeholder="e.g., ABC123"
                      value={newClass.inviteCode}
                      maxLength={10}
                      className="font-mono tracking-widest uppercase"
                      onChange={(e) =>
                        setNewClass({ ...newClass, inviteCode: e.target.value.toUpperCase() })
                      }
                    />
                    <Button
                      type="button"
                      variant="outline"
                      className="shrink-0 gap-2"
                      onClick={generateInviteCode}
                      title="Auto-generate invite code"
                    >
                      <RefreshCw className="h-4 w-4" />
                      Auto Generate
                    </Button>
                  </div>
                  <p className="text-xs text-muted-foreground">
                    Type your own code or click <strong>Auto Generate</strong> to get one from OpenTutor.
                  </p>
                </div>

                <div className="flex gap-3 pt-2">
                  <Button
                    variant="outline"
                    className="flex-1"
                    onClick={() => setShowCreateModal(false)}
                  >
                    Cancel
                  </Button>
                  <Button
                    className="flex-1"
                    onClick={handleCreateClass}
                    disabled={!newClass.title || !newClass.subject}
                  >
                    Create Classroom
                  </Button>
                </div>
              </div>
            </DialogContent>
          </Dialog>
        </div>

        {/* Stats — derived from GET /api/classrooms/teacher/{teacherId} */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-8">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Total Classrooms
              </CardTitle>
              <BookOpen className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{classrooms.length}</div>
              <p className="text-xs text-muted-foreground mt-1">All classrooms</p>
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
                {classrooms.filter((c) => c.isActive).length}
              </div>
              <p className="text-xs text-muted-foreground mt-1">Currently active</p>
            </CardContent>
          </Card>
        </div>

        {/* Classroom List — GET /api/classrooms/teacher/{teacherId} */}
        <div>
          <h2 className="text-2xl font-bold mb-4">My Classrooms</h2>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {classrooms.map((classroom) => (
              <Card
                key={classroom.id}
                className="overflow-hidden hover:shadow-lg transition-shadow group"
              >
                <Link href={`/teacher-dashboard/class/${classroom.id}`}>
                  {/* Banner Image */}
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
                </Link>

                <CardFooter className="bg-muted/30">
                  <div className="flex items-center justify-between w-full">
                    {classroom.inviteCode ? (
                      <>
                        <div className="flex items-center gap-2 text-sm">
                          <span className="text-muted-foreground">Invite Code:</span>
                          <code className="bg-background px-2 py-1 rounded font-mono font-semibold text-primary">
                            {classroom.inviteCode}
                          </code>
                        </div>
                        <Button
                          variant="ghost"
                          size="icon"
                          className="h-8 w-8"
                          onClick={() => copyInviteCode(classroom.inviteCode!)}
                        >
                          {copiedCode === classroom.inviteCode ? (
                            <Check className="h-4 w-4 text-green-600" />
                          ) : (
                            <Copy className="h-4 w-4" />
                          )}
                        </Button>
                      </>
                    ) : (
                      <span className="text-sm text-muted-foreground italic">
                        No invite code set
                      </span>
                    )}
                  </div>
                </CardFooter>
              </Card>
            ))}
          </div>
        </div>
      </main>
    </div>
  )
}
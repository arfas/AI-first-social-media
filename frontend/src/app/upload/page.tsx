'use client';

import { useState } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import withAuth from '@/components/withAuth';
import { useAuth } from '@/context/AuthContext';

function UploadPage() {
  const [caption, setCaption] = useState('');
  const [file, setFile] = useState<File | null>(null);
  const [error, setError] = useState('');
  const [isUploading, setIsUploading] = useState(false);
  const router = useRouter();
  const { token } = useAuth();

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!file) {
      setError('Please select a file to upload.');
      return;
    }
    setError('');
    setIsUploading(true);

    const formData = new FormData();
    formData.append('file', file);
    formData.append('caption', caption);
    formData.append('type', file.type.startsWith('image') ? 'IMAGE' : 'VIDEO');

    try {
      await axios.post('http://localhost:8083/content/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`,
        },
        onUploadProgress: (progressEvent) => {
          // You can use this to show a progress bar
          console.log(progressEvent.loaded, progressEvent.total);
        },
      });
      router.push('/feed');
    } catch (err: any) {
      setError(err.response?.data?.message || 'An error occurred during upload.');
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-full">
      <div className="w-full max-w-md p-8 space-y-6 bg-white rounded-lg shadow-md">
        <h2 className="text-2xl font-bold text-center">Upload Content</h2>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label
              htmlFor="caption"
              className="block text-sm font-medium text-gray-700"
            >
              Caption
            </label>
            <textarea
              id="caption"
              name="caption"
              rows={3}
              value={caption}
              onChange={(e) => setCaption(e.target.value)}
              className="w-full px-3 py-2 mt-1 border rounded-md"
            />
          </div>
          <div>
            <label
              htmlFor="file"
              className="block text-sm font-medium text-gray-700"
            >
              Media File
            </label>
            <input
              id="file"
              name="file"
              type="file"
              required
              onChange={handleFileChange}
              className="w-full px-3 py-2 mt-1 border rounded-md"
            />
          </div>
          {error && <p className="text-sm text-red-600">{error}</p>}
          {isUploading && <p className="text-sm text-blue-600">Uploading...</p>}
          <div>
            <button
              type="submit"
              className="w-full px-4 py-2 font-bold text-white bg-blue-600 rounded-md hover:bg-blue-700"
              disabled={isUploading}
            >
              {isUploading ? 'Uploading...' : 'Upload'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default withAuth(UploadPage);

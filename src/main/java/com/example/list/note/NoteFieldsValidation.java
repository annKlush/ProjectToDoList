package com.example.list.note;

public class NoteFieldsValidation {
    public static boolean isNoteFieldsValid(Note note) {
        final String title = note.getTitle().trim();
        final String content = note.getContent().trim();
        boolean isValid = false;

        if (title.length() >= 5 && title.length() <= 100 &&
            content.length() >= 5 && content.length() <= 10000) {
            isValid = true;
        }

        return isValid;
    }

    public static String getErrorMessage(Note note) {
        final String title = note.getTitle().trim();
        final String content = note.getContent().trim();
        String result = "";

        if (title.length() < 5 || title.length() > 100) {
            result = "Note name must be 5-100 symbols. ";
        }

        if (content.length() < 5 || content.length() > 10000) {
            result += "Note content must be 5-10000 symbols.";
        }

        return result;
    }
}

package com.albatros.simspriser.dao;

import com.albatros.simspriser.domain.Quiz;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class QuizDao implements DaoInterface<Quiz> {

    private static final String collection_name = "quizzes";

    public void save(Quiz quiz) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(collection_name).document(quiz.getName()).set(quiz).get();
    }

    public void delete(Quiz quiz) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(collection_name).document(quiz.getName()).delete().get();
    }

    public List<Quiz> getAll() throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Quiz> res = new ArrayList<>();
        Iterable<DocumentReference> refs = firestore.collection(collection_name).listDocuments();
        for (DocumentReference ref : refs) {
            DocumentSnapshot doc = ref.get().get();
            Quiz item = doc.toObject(Quiz.class);
            res.add(item);
        }
        return res;
    }
}

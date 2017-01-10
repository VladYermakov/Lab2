package com.yermakov.xplatform.lesson7.model;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable{

    private String mQuestion;
    private String mPhotoLink;
    private List<String> mVariants;
    private List<Boolean> mAnswers;

    /*public Bitmap getPhoto() throws IOException {
        // TODO: download Bitmap image
        StorageReference ref = FirebaseStorage.getInstance().getReference();
        File localFile = File.createTempFile(mPhotoLink, ".jpg");

        ref.getFile(localFile);

        return BitmapFactory.decodeStream(new FileInputStream(localFile));
    }*/

    public Test() { }

    public Test(String question, List<String> variants, List<Boolean> answers) {
        this(question, null, variants, answers);
    }

    public Test(String question, String photoLink, List<String> variants, List<Boolean> answers) {
        mQuestion = question;
        mPhotoLink = photoLink;
        mVariants = variants;
        mAnswers = answers;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getVariant(int i) {
        return mVariants.get(i);
    }

    public List<String> getVariants() {
        return mVariants;
    }

    public void setVariants(List<String> variants) {
        mVariants = variants;
    }

    public Boolean getAnswer(int i) {
        return mAnswers.get(i);
    }

    public List<Boolean> getAnswers() {
        return mAnswers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.mAnswers = answers;
    }
}

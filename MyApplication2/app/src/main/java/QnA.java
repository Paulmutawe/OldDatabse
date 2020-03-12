public class QnA {

    private static int id;
    private String question;
    private String answer;

    public QnA() {

    }

    public QnA(int id,String question,String answer) {

        this.id = id;
        this.question = question;
        this.answer = answer;

    }

    public void setId(int id) {

        this.id = id;

    }

    public void setQuestion(String question) {

        this.question = question;

    }

    public void setAnswer(String answer) {

        this.answer = answer;

    }

    public static int getId() {

        return id;

    }

    public String getAnswer() {

        return answer;

    }

    public String getQuestion() {

        return question;

    }
}

data class Post(
    val id: Int,
    val content: String,
    val likes: Int = 0,
    val attachments: Array<Attachment> = emptyArray()
) {
    fun printContent() {
        println("Post with $id printed it content: $content")
    }
}

data class Audio(
    val id: Int,
    val name: String
)

data class Video(
    val id: Int,
    val length: Int
)

interface Attachment {
    val type: String
}

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type = "audio"
}

data class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post) {
        posts += post
    }

    fun like(postId: Int): Boolean {
        var result = false
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                posts[index] = post.copy(likes = post.likes + 1)
                result = true
            }
        }
        return result
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
        println()
    }
}

fun main() {
    val post = Post(1, "Text", attachments = arrayOf(AudioAttachment(Audio(1, "song.mp3"))))
    post.printContent()
    println((post.attachments[0] as AudioAttachment).audio)
    WallService.add(post)
    WallService.add(
        Post(
            2, "Another text",
            attachments = arrayOf(
                AudioAttachment(Audio(2, "another_song.mp3")),
                VideoAttachment(Video(2, 60))
            )
        )
    )
    WallService.print()
    WallService.like(1)
    WallService.print()
}
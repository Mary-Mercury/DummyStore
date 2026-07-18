package com.example.dummystore.data

import com.example.dummystore.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor() {

    private val articles = listOf(
        Article(
            id = 1,
            title = "How to Choose a Laptop for Everyday Use",
            description = "We explain what to look for when buying a laptop for work, study, and entertainment without overpaying for unnecessary features.",
            content = "Choosing a laptop is easier if you first decide what you will use it for. For browsing the internet, watching videos, working with documents, and online meetings, an entry-level or mid-range model is usually enough. Pay attention to the processor, RAM, and SSD storage, as these have the biggest impact on everyday performance. A Full HD display and a battery that lasts at least 8 hours will make the laptop much more comfortable to use. If you don't play modern games or edit videos professionally, there's usually no need to spend extra money on expensive hardware.",
            image = R.drawable.laptop_everyday
        ),

        Article(
            id = 2,
            title = "SSD or HDD: What's the Difference?",
            description = "Learn how SSD and HDD drives differ and which one is the better choice for most users today.",
            content = "An SSD stores data on memory chips, while an HDD uses spinning magnetic disks. Because of this, SSDs are much faster: your computer starts in seconds, programs open quicker, and files copy faster. HDDs still have one advantage—they usually offer more storage for the same price. If you're buying a new computer, an SSD is the best choice for the operating system and everyday work. If you need a lot of space for photos, videos, or backups, you can also add an HDD as extra storage.",
            image = R.drawable.ssd
        ),

        Article(
            id = 3,
            title = "How Much RAM Do You Really Need?",
            description = "We explain how RAM affects your computer's performance and how much memory is enough for different tasks.",
            content = "RAM allows your computer to keep programs running smoothly without slowing down. Today, 8 GB is enough for web browsing, office work, and watching videos, while 16 GB is a better choice if you often use many applications at once or play games. More RAM helps your system stay responsive when multitasking, but buying more than you actually need won't noticeably improve everyday performance. It's always a good idea to check whether your computer allows future memory upgrades.",
            image = R.drawable.ram
        ),

        Article(
            id = 4,
            title = "How to Make Your Computer Run Faster",
            description = "Simple tips that can improve your computer's speed without buying a new device.",
            content = "Over time, computers often become slower because unnecessary programs start automatically, storage becomes almost full, or the operating system needs updates. Start by removing applications you no longer use and disabling unnecessary startup programs. Keep at least 15–20% of your SSD or HDD free for better performance. Installing system updates and restarting your computer regularly can also solve many small issues. If your computer still feels slow, upgrading to an SSD or adding more RAM can provide a noticeable improvement.",
            image = R.drawable.faster_computer
        ),

        Article(
            id = 5,
            title = "Wi-Fi Problems? Here's What to Check First",
            description = "Common reasons why your wireless internet may be slow and easy ways to fix the problem.",
            content = "A weak Wi-Fi signal is not always caused by your internet provider. The location of your router, thick walls, or too many connected devices can reduce connection quality. Try placing the router in a more open and central location, then restart it if the connection becomes unstable. Updating the router's firmware may also improve performance. If your home is large, a Wi-Fi repeater or mesh system can help provide better coverage in every room.",
            image = R.drawable.wifi_problems
        ),

        Article(
            id = 6,
            title = "How to Keep Your Laptop Battery Healthy",
            description = "Useful habits that can help your laptop battery last longer and maintain good performance.",
            content = "Modern laptop batteries are designed to last for many charging cycles, but a few simple habits can extend their lifespan. Avoid letting the battery completely discharge on a regular basis, and try not to leave the laptop in very hot places. If you mostly use the laptop at home, some manufacturers allow you to limit charging to around 80%, which helps reduce battery wear. Keeping the operating system updated and lowering screen brightness when possible can also improve battery life during everyday use.",
            image = R.drawable.laptop_battery
        ),
    )

    fun getArticles(): List<Article> = articles

    fun getArticle(id: Int): Article? =
        articles.find { it.id == id }
}
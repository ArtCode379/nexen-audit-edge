package nexenstrategy.technology.nexenauditedge.data.repository

import nexenstrategy.technology.nexenauditedge.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services = listOf(
        service(
            1, "Cybersecurity Risk Audit",
            "A structured review of vulnerabilities, controls, and operational exposure across your digital estate.",
            780.0, "Cybersecurity", 90, "photo-1563013544-824ae1b704d3",
            listOf("Threat surface review", "Control maturity score", "Prioritised remediation plan"),
        ),
        service(
            2, "Cloud Readiness Assessment",
            "Build a practical migration path grounded in cost, resilience, governance, and team capability.",
            640.0, "Cloud Solutions", 75, "photo-1451187580459-43490279c0fa",
            listOf("Workload discovery", "Cloud cost model", "Migration roadmap"),
        ),
        service(
            3, "Digital Strategy Workshop",
            "Align technology investment with measurable business outcomes in a focused leadership session.",
            520.0, "IT Strategy", 120, "photo-1552664730-d307ca884978",
            listOf("Executive workshop", "Capability mapping", "90-day action plan"),
        ),
        service(
            4, "Business Process Optimisation",
            "Identify friction, duplicated work, and automation opportunities across critical workflows.",
            590.0, "Optimisation", 90, "photo-1551288049-bebda4e38f71",
            listOf("Process mapping", "Automation shortlist", "Benefit forecast"),
        ),
        service(
            5, "Data Governance Review",
            "Establish clear ownership, quality standards, and controls for trusted business data.",
            710.0, "Data & AI", 90, "photo-1558494949-ef010cbdcc31",
            listOf("Data inventory", "Governance gaps", "Operating model"),
        ),
        service(
            6, "AI Opportunity Scan",
            "Find responsible, high-value AI use cases and define the foundations required to deliver them.",
            680.0, "Data & AI", 75, "photo-1677442136019-21780ecad995",
            listOf("Use-case scoring", "Risk screening", "Pilot blueprint"),
        ),
        service(
            7, "Technology Due Diligence",
            "Independent assessment of architecture, delivery health, security, and technical debt.",
            950.0, "IT Strategy", 120, "photo-1556761175-b413da4baf72",
            listOf("Architecture review", "Delivery assessment", "Investment risk report"),
        ),
        service(
            8, "Incident Response Planning",
            "Prepare teams to respond decisively with tested roles, playbooks, and communications.",
            760.0, "Cybersecurity", 90, "photo-1516321318423-f06f85e504b3",
            listOf("Scenario workshop", "Response playbook", "Tabletop exercise"),
        ),
        service(
            9, "Cloud Cost Optimisation",
            "Turn cloud billing and usage data into durable savings without compromising performance.",
            610.0, "Cloud Solutions", 75, "photo-1460925895917-afdab827c52f",
            listOf("Spend baseline", "Waste analysis", "Savings backlog"),
        ),
        service(
            10, "Vendor & Platform Selection",
            "Select technology partners through transparent requirements, scoring, and risk analysis.",
            560.0, "Optimisation", 90, "photo-1521737711867-e3b97375f902",
            listOf("Requirements design", "Vendor scorecard", "Recommendation brief"),
        ),
    )

    private fun service(id: Int, name: String, description: String, price: Double, category: String, duration: Int, photo: String, features: List<String>) = ServiceModel(
        id = id,
        name = name,
        description = description,
        price = price,
        availableTime = listOf(LocalTime.of(9, 30), LocalTime.of(11, 0), LocalTime.of(14, 30)),
        imageUrl = "https://images.unsplash.com/$photo?auto=format&fit=crop&w=1200&q=85",
        category = category,
        durationMinutes = duration,
        features = features,
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}

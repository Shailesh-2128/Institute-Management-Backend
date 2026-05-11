package com.InstituteManagement.InstituteManagement.analytics.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@Tag(name="Analytics Api's", description = "System analytics")
public class AnalyticsController {

    @Operation(summary = "Get monthly admissions analytics")
    @GetMapping("/admissions/monthly")
    public ApiResponse<Object> getMonthlyAdmissions() {
        return ApiResponse.<Object>builder().success(true).data("Monthly Admissions").message("Fetched").build();
    }

    @Operation(summary = "Get monthly revenue analytics")
    @GetMapping("/revenue/monthly")
    public ApiResponse<Object> getMonthlyRevenue() {
        return ApiResponse.<Object>builder().success(true).data("Monthly Revenue").message("Fetched").build();
    }

    @Operation(summary = "Get overall attendance analytics")
    @GetMapping("/attendance/overall")
    public ApiResponse<Object> getOverallAttendance() {
        return ApiResponse.<Object>builder().success(true).data("Overall Attendance").message("Fetched").build();
    }

    @Operation(summary = "Get course popularity analytics")
    @GetMapping("/course/popularity")
    public ApiResponse<Object> getCoursePopularity() {
        return ApiResponse.<Object>builder().success(true).data("Course Popularity").message("Fetched").build();
    }

    @Operation(summary = "Get student growth analytics")
    @GetMapping("/student-growth")
    public ApiResponse<Object> getStudentGrowth() {
        return ApiResponse.<Object>builder().success(true).data("Student Growth").message("Fetched").build();
    }
}

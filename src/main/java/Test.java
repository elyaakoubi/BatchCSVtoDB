import java.util.Date;

import org.ensa.dao.Idao;
import org.ensa.entities.Personne;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("org/ensa/config/SpringConf.xml");
		
		
		Job job = (Job) ctx.getBean("importPersonnes");
        JobParametersBuilder jobBuilder= new JobParametersBuilder();
        jobBuilder.addString("Addpersonne","Personne ajout�");
        jobBuilder.addLong("Current Time", System.currentTimeMillis());
        JobParameters jobParameters = jobBuilder.toJobParameters();
		JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher"); 
		JobExecution execution = jobLauncher.run(job, jobParameters);
		System.out.println("Completion Status : " + execution.getStatus());

	}
}
